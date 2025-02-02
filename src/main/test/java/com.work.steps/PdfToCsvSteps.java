public class PdfToCsvSteps{

    private String pdfFilePath;
    private String csvFilePath;
    private String txtFilePath;
    private List<Map<String,String>> parsedTxtData;
    private List<String> extractedText;

    @Given("I extract text from PDF {string}")
    public void iExtractTextFromPDF(String arg0) throws IOExcpetion{
        try{

            extractedText=PdfRaderUtil.extractedTextFromPdf(arg0);
            if(extractedText == null || extractedText.isEmpty()){
                System.out.println("No text extracted from the pdf.")
                extractedText = new ArrayList<>();
            }
            else
            {
                System.out.println("Extracted text from PDF : ");
                extractedText.forEach(System.out::println);
            }
catch(IOExcpetion e){
                System.err.println("An occured while extracting pdf: "+ e.getMessage());
                extractedText = new ArrayList<>();
            }
        }
    }



}