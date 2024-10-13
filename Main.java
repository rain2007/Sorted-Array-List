import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //create a sorted ArrayList of YearlyWaterData
        SortedArrayList<YearlyWaterRecord> waterArrayList = new SortedArrayList<>();

        File waterDataFile = new File("IsoYearWaterData.txt");

        int[] years = new int[0];
        //todo use toArrays to turn arraylist into array
        try {
            Scanner scan = new Scanner(waterDataFile);
            scan.nextLine(); // gets rid of the line with the isoYear,BasicPlusPct,etc...
            SortedArrayList<Integer> yearsArrayList = new SortedArrayList<>();

            while (scan.hasNext()) {

                String[] sortedArrayList = scan.nextLine().split(",");

                String isoYear = sortedArrayList[0];
                String yearsString = isoYear.substring(3);
                int yearsData = Integer.parseInt(yearsString);
                if (!yearsArrayList.contains(yearsData)) {
                    yearsArrayList.add(yearsData);
                }

                double basicPlusPct = Double.parseDouble(translation(sortedArrayList[1]));
                double limitedPct = Double.parseDouble(translation(sortedArrayList[2]));
                double unimprovedPct = Double.parseDouble(translation(sortedArrayList[3]));
                double surfacePct = Double.parseDouble(translation(sortedArrayList[4]));

                YearlyWaterRecord waterData = new YearlyWaterRecord(isoYear, basicPlusPct, limitedPct, unimprovedPct, surfacePct);

                waterArrayList.add(waterData);
            }

            years = new int[yearsArrayList.size()];
            for (int i = 0; i < years.length; i++) {
                years[i] = yearsArrayList.get(i);
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        File countriesIsoCodeDataFile = new File("CountriesAndIsoCodes.txt");

        String[] countries = new String[0];
        String[] isoCodes = new String[0];

        try {
            Scanner scan = new Scanner(countriesIsoCodeDataFile);
            int arraySize = Integer.parseInt(scan.nextLine());

            countries = new String[arraySize];
            isoCodes = new String[arraySize];

            scan.nextLine(); // gets rid of the line with the Country#ISO Code

            for (int i = 0; i < arraySize; i++) {
                String[] isoCountry = scan.nextLine().split("#");
                countries[i] = isoCountry[0];
                isoCodes[i] = isoCountry[1];
            }

            System.out.println(Arrays.toString(countries));
            System.out.println(Arrays.toString(isoCodes));

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        new WaterComparisonGui(waterArrayList, countries, isoCodes, years);
    }

    public static String translation(String value) {
        if (value.equals(">99")) {
            value = "100";
        } else if (value.equals("<1")) {
            value = "0";
        } else if (value.equals("-")){
            value = "-1";
        } else {
            return value;
        }
        return value;
    }

}
