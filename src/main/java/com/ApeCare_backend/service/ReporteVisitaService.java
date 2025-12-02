package com.ApeCare_backend.service;

import com.itextpdf.text.DocumentException;

public interface ReporteVisitaService {
    byte[] generarReporteVisitaPDF(Long visitaId) throws DocumentException;
    byte[] generarReporteVisitasPDF(Long medicoId, Long pacienteId, String fecha);
}
