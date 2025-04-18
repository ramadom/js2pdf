package lt.darwa.js2pdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfUtils {

  public static byte[] html2pdf(String html) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    PdfRendererBuilder builder = new PdfRendererBuilder();
    builder.defaultTextDirection(PdfRendererBuilder.TextDirection.LTR);
    builder.withHtmlContent(html, null);
    //builder.useFastMode(); // Default disablinta, fast-mode neprocesina image'u
    builder.toStream(os);
    builder.run();
    return os.toByteArray();
  }


}
