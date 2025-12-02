package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.entity.MedicamentoSuministrado;
import com.ApeCare_backend.entity.RegistroVisita;
import com.ApeCare_backend.entity.RegistroVisitaSintoma;
import com.ApeCare_backend.repository.MedicoRepository;
import com.ApeCare_backend.repository.PacienteRepository;
import com.ApeCare_backend.repository.RegistroVisitaRepository;
import com.ApeCare_backend.service.ReporteVisitaService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteVisitaServiceImpl implements ReporteVisitaService {

    private final RegistroVisitaRepository visitaRepo;
    private final MedicoRepository medicoRepo;
    private final PacienteRepository pacienteRepo;

    @Override
    public byte[] generarReporteVisitaPDF(Long visitaId) throws DocumentException {
        RegistroVisita visita = visitaRepo.findById(visitaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Visita no encontrada"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        agregarSoloLogo(document);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        addRow(table, "Fecha", visita.getFechaVisita().toString());
        addRow(table, "Hora", String.valueOf(visita.getHoraVisita()));
        addRow(table, "Estado", visita.getEstado().getNombre());
        addRow(table, "Recomendaciones", visita.getRecomendaciones());

        addRow(table, "Paciente", visita.getPaciente().getNombre() +
                " | Edad: " + visita.getPaciente().getEdad() +
                " | Cédula: " + visita.getPaciente().getCedula() +
                " | Carnet: " + visita.getPaciente().getNumeroCarnet());

        addRow(table, "Médico", visita.getMedico().getNombre() +
                " | Cédula: " + visita.getMedico().getCedula() +
                " | Especialidad: " + visita.getMedico().getEspecialidad().getNombre());

        String sintomas = visita.getSintomas().stream()
                .map(s -> s.getSintoma().getNombre() + " (" + s.getSintoma().getDescripcion() + ")")
                .collect(Collectors.joining(", "));
        addRow(table, "Síntomas", sintomas);

        String medicamentos = visita.getMedicamentosEntregados().stream()
                .map(m -> m.getMedicamento().getDescripcion() + " (" + m.getCantidadSuministrada() + ")")
                .collect(Collectors.joining(", "));
        addRow(table, "Medicamentos", medicamentos);

        document.add(table);
        document.close();
        return out.toByteArray();
    }

    public byte[] generarReporteVisitasPDF(Long medicoId, Long pacienteId, String fecha) {
        List<RegistroVisita> visitas = visitaRepo.findAll();

        if (medicoId != null) {
            visitas = visitas.stream()
                    .filter(v -> v.getMedico().getId().equals(medicoId))
                    .collect(Collectors.toList());
        }
        if (pacienteId != null) {
            visitas = visitas.stream()
                    .filter(v -> v.getPaciente().getId().equals(pacienteId))
                    .collect(Collectors.toList());
        }
        if (fecha != null) {
            visitas = visitas.stream()
                    .filter(v -> v.getFechaVisita().toString().equals(fecha))
                    .collect(Collectors.toList());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            agregarSoloLogo(document);

            if (visitas.isEmpty()) {
                String filtros = "";
                if (medicoId != null) {
                    String nombreMedico = medicoRepo.findById(medicoId)
                            .map(m -> m.getNombre())
                            .orElse("Médico no encontrado");
                    filtros += "Médico: " + nombreMedico + " ";
                }
                if (pacienteId != null) {
                    String nombrePaciente = pacienteRepo.findById(pacienteId)
                            .map(p -> p.getNombre())
                            .orElse("Paciente no encontrado");
                    filtros += "Paciente: " + nombrePaciente + " ";
                }
                if (fecha != null) {
                    filtros += "Fecha: " + fecha + " ";
                }
                if (filtros.isEmpty()) {
                    filtros = "Sin filtros aplicados";
                }

                document.add(new Paragraph("No se encontraron visitas para los filtros aplicados."));
                document.add(new Paragraph("Filtros: " + filtros));
                document.close();
                return out.toByteArray();
            }

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            Font thFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            BaseColor azulSalud = new BaseColor(33, 158, 188);
            String[] headers = {"ID", "Paciente", "Médico", "Fecha", "Síntomas", "Medicamentos"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(h, thFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(azulSalud);
                table.addCell(cell);
            }

            Font tdFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
            for (RegistroVisita v : visitas) {
                table.addCell(new Paragraph(String.valueOf(v.getId()), tdFont));
                table.addCell(new Paragraph(v.getPaciente().getNombre(), tdFont));
                table.addCell(new Paragraph(v.getMedico().getNombre(), tdFont));
                table.addCell(new Paragraph(v.getFechaVisita().toLocalDate() + " " + v.getHoraVisita(), tdFont));

                String sintomas = v.getSintomas().stream()
                        .map(s -> s.getSintoma().getNombre())
                        .collect(Collectors.joining(", "));
                table.addCell(new Paragraph(sintomas, tdFont));

                String medicamentos = v.getMedicamentosEntregados().stream()
                        .map(m -> m.getMedicamento().getDescripcion() + " (" + m.getCantidadSuministrada() + ")")
                        .collect(Collectors.joining(", "));
                table.addCell(new Paragraph(medicamentos, tdFont));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al generar el PDF", e);
        }

        return out.toByteArray();
    }

    private void addRow(PdfPTable table, String key, String value) {
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

        PdfPCell cellKey = new PdfPCell(new Paragraph(key, headerFont));
        PdfPCell cellValue = new PdfPCell(new Paragraph(value != null ? value : "-", dataFont));

        table.addCell(cellKey);
        table.addCell(cellValue);
    }

    private void agregarSoloLogo(Document document) {
        try {
            URL logoUrl = getClass().getResource("/static/logo_apecare.png");
            if (logoUrl != null) {
                Image logo = Image.getInstance(logoUrl);
                logo.scaleToFit(80, 80);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);
                document.add(new Paragraph(" "));
            }
        } catch (Exception e) {
            // Si falla el logo corre igual
        }
    }

}
