//Needed imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CombineCSVs {
    public static void main(String[] args) {
        //try to open files and output to combined csv
        try {

            //boolean for checking if any first line has been read
            boolean prev_first_line_read = false;

            //for each file
            for (String s: args) {

                //open file
                File myObj = new File(s);
                //create scanner to read file
                Scanner myReader = new Scanner(myObj);
                //current line number of scanner 
                int line_number = 1;

                //for each line in the scanner
                while (myReader.hasNextLine()) {

                    //parse the line
                    String data = myReader.nextLine();

                    //when on the first line for a file
                    if (line_number == 1) {

                        //add the column descriptions for all CSVs 
                        //only do this if it had never been done for a previous file
                        if (!prev_first_line_read) {

                            //update boolean for checking if any first line has been read
                            prev_first_line_read = true;
                            //add filename column 
                            data += "," + "\"filename\"";
                            //add line to combined CSV
                            System.out.println(data);

                        }
                        //update line number to -1 to show the current file's scanner...
                        //is no longer on the first line
                        line_number = -1;
                    }
                    //when NOT on the first line for a file
                    else {
                        //get position of / from command line arguement
                        int pos_before_filename = s.lastIndexOf('/');

                        //if just actual file name was given
                        if (pos_before_filename == -1){
                          
                          //add source file name to data by adding the command line arguement
                          data += "," + "\"" + s + "\"";
                          //add line to combined CSV
                          System.out.println(data);

                        }
                        //else, when file path is given
                        else{

                          //add source file name to data be stripping everything from command... 
                          //line arguement except actual file name 
                          data += "," + "\"" + s.substring(pos_before_filename+1) + "\"";
                          //add line to combined CSV
                          System.out.println(data);

                        }    
                    }
                }
                //close scanner
                myReader.close();
            }
        }
        //catch error of file from arguement not exisiting
        catch (FileNotFoundException e) {
            System.out.println("An error occurred. A file from the arguements could not be found.");
        } 
        //catch all other errors
        catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
}