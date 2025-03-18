package com.daanidev.kmp_pdf

import androidx.compose.runtime.Composable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PDFUtil {
    /**
     * Generate PDF from a scrollable composable and optionally share it
     * @param fileName Name of the output PDF file
     * @param content The composable content to render to PDF
     * @param shareAfterCreation Whether to share the PDF after creation
     * @return Path to the created PDF or null if generation failed
     */
    suspend fun generatePdf(
        fileName: String,
        content: @Composable () -> Unit,
        shareAfterCreation: Boolean = false,
    ): String? = withContext(Dispatchers.Default) {
        val generator = PDFGenerator()
        val pdfPath = generator.createPdfFromComposable(fileName, content)

        if (shareAfterCreation && pdfPath != null) {
            generator.sharePdf(pdfPath)
        }

        return@withContext pdfPath
    }
}