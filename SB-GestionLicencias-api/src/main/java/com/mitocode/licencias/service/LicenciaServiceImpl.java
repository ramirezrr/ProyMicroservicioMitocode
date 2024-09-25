package com.mitocode.licencias.service;

import com.mitocode.licencias.exception.ErrorGenericException;
import com.mitocode.licencias.exception.FunctionalGenericResponse;
import com.mitocode.licencias.model.BajaResponse;
import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.model.TipoLicencia;
import com.mitocode.licencias.model.Titular;
import com.mitocode.licencias.repository.LicenciaRepository;
import com.mitocode.licencias.util.Patcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class LicenciaServiceImpl implements ILicenciaService {


    private final LicenciaRepository licenciaRepository;
    private final ITitularService titularService;
    private final ITipoLicenciaService tipoLicenciaService;

    @Override
    public FunctionalGenericResponse validarEstadoLicencia(String numeroLicencia) {

        FunctionalGenericResponse response = new FunctionalGenericResponse();

        response.setCodigoRespuesta(HttpStatus.OK.toString());
        response.setMensajeRespuesta(validarFecha(numeroLicencia, null).getMensajeRespuesta());

        return response;
    }

    public FunctionalGenericResponse validarFecha(String numeroLicencia, Long id) {
        FunctionalGenericResponse response = new FunctionalGenericResponse();

        Optional<Licencia> licencia = licenciaRepository.findByNumeroLicenceOrIdAndDeleted(numeroLicencia, id, false);
        if (licencia.isPresent()) {

            LocalDate fechaVencimiento = licencia.get().getFechaVencimiento();
            LocalDate fechaActual = LocalDate.now();
            StringBuilder mensaje = new StringBuilder();

            if (fechaActual.isAfter(fechaVencimiento)) {
                mensaje.append("Tu licencia ya venció. ");

                long diasVencidos = ChronoUnit.DAYS.between(fechaVencimiento, fechaActual);

                if (diasVencidos > 30) {
                    response.setCodigoRespuesta("2");
                    mensaje.append("Debes sacar una nueva licencia.");
                } else {
                    response.setCodigoRespuesta("1");
                    mensaje.append("Tienes ").append(30 - diasVencidos).append(" días para renovarla.");
                }
            } else if (fechaActual.equals(fechaVencimiento)) {
                mensaje.append("Tu licencia vence hoy.");
                response.setCodigoRespuesta("0");
            } else {
                response.setCodigoRespuesta("0");
                mensaje.append("Tu licencia aún no ha vencido.");
            }
//            response.setCodigoRespuesta(HttpStatus.OK.toString());
            response.setMensajeRespuesta(mensaje.toString());
        } else {
            return new FunctionalGenericResponse(HttpStatus.BAD_REQUEST.toString(), "No se pudo obtener los datos de la licencia correctamente.");
        }
        return response;

    }

    public Licencia emitirLicencia(Licencia licencia) {
        licencia.setFechaEmision(LocalDate.now());
        licencia.setFechaVencimiento(licencia.getFechaEmision().plusYears(5));
        licencia.setDeleted(false);
        licencia.setNumLicencia(generarCodigoLicencia(licenciaRepository.getNextLicenciaSequence().orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Error al emitir licencia."))));
        licencia.setTitular(titularService.findByNumeroDocumento(licencia.getTitular().getNumeroDocumento()));
        licencia.setTipoLicencia(tipoLicenciaService.getTipoLicenciaById(licencia.getTipoLicencia().getId()));
        return licenciaRepository.save(licencia);
    }

    public String generarCodigoLicencia(Long id) {
        String formato = "%08d"; // 0 a la izquierda, longitud fija de 8 dígitos
        return "L" + String.format(formato, id);
    }


    public Licencia actualizarLicencia(Long id, Licencia licenciaActualizada) {
        Licencia licencia = licenciaRepository.findByDeletedAndId(false, id).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencia no encontrada"));

        licencia.setNumLicencia(licenciaActualizada.getNumLicencia());
        licencia.setFechaEmision(licenciaActualizada.getFechaEmision());
        licencia.setFechaVencimiento(licenciaActualizada.getFechaVencimiento());
        licencia.setEstado(licenciaActualizada.getEstado());
        licencia.setDeleted(licenciaActualizada.isDeleted());

        if (licenciaActualizada.getTitular() != null && licenciaActualizada.getTitular().getId() != null) {
            Titular titular = titularService.getTitularById(licenciaActualizada.getTitular().getId());
            titular.setId(licenciaActualizada.getTitular().getId());
            titular.setNumeroDocumento(licenciaActualizada.getTitular().getNumeroDocumento());
            titular.setNombres(licenciaActualizada.getTitular().getNombres());
            titular.setApellidos(licenciaActualizada.getTitular().getApellidos());
            titular.setFechaNacimiento(licenciaActualizada.getTitular().getFechaNacimiento());
            titular.setDireccion(licenciaActualizada.getTitular().getDireccion());
            Titular titularActualizado = titularService.saveTitular(titular);
            licencia.setTitular(titularActualizado);
        }
        if (licenciaActualizada.getTipoLicencia() != null && licenciaActualizada.getTipoLicencia().getId() != null) {
            TipoLicencia tipoLicencia = tipoLicenciaService.getTipoLicenciaById(licenciaActualizada.getTipoLicencia().getId());
            tipoLicencia.setCategoria(licenciaActualizada.getTipoLicencia().getCategoria());
            tipoLicencia.setRestricciones(licenciaActualizada.getTipoLicencia().getRestricciones());
            TipoLicencia titularActualizado = tipoLicenciaService.saveTipoLicencia(tipoLicencia);
            licencia.setTipoLicencia(titularActualizado);
        }

        return licenciaRepository.save(licencia);
    }

    public List<Licencia> listarLicencias() {
        return licenciaRepository.findAllByDeleted(false).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencias no encontradas."));
    }

    public Licencia listarLicenciasPorId(Long id) {
        return licenciaRepository.findByDeletedAndId(false, id).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencia no encontrada id: " + id));
    }

    public List<Licencia> listarLicenciasPorEstado(String estado) {
        return licenciaRepository.findByDeletedAndEstado(false, estado).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencias no encontradas con estado: " + estado));
    }

    public List<Licencia> listarLicenciasEliminadas() {
        return licenciaRepository.findAllByDeleted(true).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencias no encontradas."));
    }


    public Licencia actualizarValorParcialesLicencia(Long id, Licencia incompleteLicencia) {

        Licencia existingLicencia = licenciaRepository.findByDeletedAndId(false, id).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencia no encontrada id: " + id));
        try {
            Patcher.licenciaPatcher(existingLicencia, incompleteLicencia);
            licenciaRepository.save(existingLicencia);
        } catch (Exception e) {
            throw new ErrorGenericException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Ocurrió un error inesperado al actualizar la licencia: " + id);
        }
        return existingLicencia;
    }

    public BajaResponse actualizarEstadoDarDeBajaLicencia(String numeroLicencia, Long id) {

        if (numeroLicencia == null && id == null) {
            throw new ErrorGenericException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Ocurrió un error inesperado al actualizar la licencia: " + id);
        }

        Licencia incompleteLicencia = new Licencia();
        BajaResponse bajaResponse = new BajaResponse();
        FunctionalGenericResponse response = validarFecha(numeroLicencia, id);

        Integer codRespuesta = Integer.parseInt(response.getCodigoRespuesta());

        if (codRespuesta == 1) {
            bajaResponse.setProceso("Se realizó el cambio de estado a INACTIVO.");
            incompleteLicencia.setEstado("INACTIVO");
        } else if (codRespuesta == 2) {
            bajaResponse.setProceso("Se realizó el cambio de estado a INACTIVO y se dió de baja la licencia.");
            incompleteLicencia.setEstado("INACTIVO");
            incompleteLicencia.setDeleted(true);
        } else if (codRespuesta == 0) {
            bajaResponse.setProceso("No se realizó ninguna acción.");
            incompleteLicencia.setEstado("ACTIVO");
        }

        String num = id == null ? numeroLicencia : id.toString();
        Licencia existingLicencia = licenciaRepository.findByNumeroLicenceOrIdAndDeleted(numeroLicencia, id, false).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencia no encontrada: " + num));

        try {
            Patcher.licenciaPatcher(existingLicencia, incompleteLicencia);
            licenciaRepository.save(existingLicencia);
            bajaResponse.setLicencia(existingLicencia);
            bajaResponse.setCodigoRespuesta(HttpStatus.OK.toString());
            bajaResponse.setMensajeRespuesta(response.getMensajeRespuesta());

        } catch (Exception e) {
            throw new ErrorGenericException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Ocurrió un error inesperado al actualizar la licencia: " + id);
        }
        return bajaResponse;
    }

}
