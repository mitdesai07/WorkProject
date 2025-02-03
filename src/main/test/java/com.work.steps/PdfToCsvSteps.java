public class PdfToCsvSteps{

    private String pdfFilePath;
    private String csvFilePath;
    private String txtFilePath;
    private List<Map<String,String>> parsedTxtData;
    private List<String> extractedText;

    @Given("i have a TXT file at {string}")
    public void iHaveATXTFileAt(String arg0){
        txtFilePath=arg0;
    }

    @When("I parse the TXT file")
    public void iParseTheTXTFile(){
        parsedTxtData=TxtParserUtil.parseTxtFile(txtFilePath);
        System.out.println("Parsed TXT data :");
        for(Map<String, String> record : parsedTxtData){
            System.out.println(record);
        }
    }

    @Then(I should see the extracted data printed in the logs)
    public void IShouldSeeTheExtractedDataPrintedInTheLogs(){
        if(parsedTxtData != null || !parsedTxtData.isEmpty()){
            System.out.println("Txt file was parsed successfully with : "+ parsedTxtData.size() + "records.");
        }else{
            throw new AssertionError("No data was parsed from TXT file.")
        }
    }

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

@Then("I write the extracted text to CSV {string}")
public void IWriteTheExtractedTextToCsv(String arg0) throws IOException{
        if(extractedText == nill || extractedText.isEmpty()){
            System.out.println ("No text available to write on CSV.");
            return;
        }
        List<String[]> data new ArrayList<>();
        for(String line : extractedText){
            data.add(new String[]{line});
        }
        try{
            CsvWriteUtil.writeToCsv(data, arg0);
            System.out.println ("Csv Written to : "+arg0);
        }catch(IOException e){
            System.out.println ("An error occured while writting text to csv" + e.getMessage());
        }
}
}