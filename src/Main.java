import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static boolean hasEveryMatrixOnlyOneO(ArrayList<ArrayList<char[]>> inputMatrixArr) {
        int oNum = 0, inputArraySize = inputMatrixArr.size();

        for (ArrayList<char[]> a : inputMatrixArr
        ) {
            for (char[] b : a
            ) {
                for (char c : b
                ) {
                    if (c == 'o') {
                        oNum++;
                    }
                }
            }
        }
        return oNum == inputArraySize;


    }

    public static boolean hasThisMatrixOnlyOneO(ArrayList<char[]> inputMatrix) {
        int oNum = 0, inputArraySize = inputMatrix.size();

        for (char[] a : inputMatrix
        ) {
            for (char b : a
            ) {
                if (b == 'o') {
                    oNum++;
                }
            }
        }
        return oNum == 1;


    }

    public static boolean hasThisMatrixCorrectSperO(ArrayList<char[]> inputMatrix, int sPerO) {
        int linNumber, colNumber = 0, S, oNum = 0;

        linNumber = inputMatrix.size();

        for (char a : inputMatrix.get(0)
        ) {
            colNumber++;
        }

        S = linNumber * colNumber;

        for (char[] a : inputMatrix
        ) {
            for (char b : a
            ) {
                if (b == 'o') {
                    oNum++;
                }
            }
        }

        if (oNum == 0) {
            return false;
        } else {
            return (S / oNum) == sPerO;
        }

    }

    public static boolean hasEveryMatrixCorrectSperO(ArrayList<ArrayList<char[]>> inputMatrixArr, int sPerO) {

        boolean flag = true;

        for (ArrayList<char[]> a : inputMatrixArr
        ) {
            if (!hasThisMatrixCorrectSperO(a, sPerO)) {
                flag = false;
                return flag;
            }
        }

        return flag;
    }

    public static ArrayList<ArrayList<char[]>> splitHorizontallyfromTopOn(ArrayList<char[]> inputMatrix, int fromTopOn) {
        ArrayList<ArrayList<char[]>> outputMatrixArr = new ArrayList<>();
        ArrayList<char[]> partOfOutput = new ArrayList<>();
        ArrayList<char[]> partOfOutput2 = new ArrayList<>();
        int i = 0;

        if (fromTopOn == 0) {
            outputMatrixArr.add(inputMatrix);
            return outputMatrixArr;
        }

        if (fromTopOn >= inputMatrix.size()) {
            outputMatrixArr.add(inputMatrix);
            return outputMatrixArr;
        }

        for (char[] a : inputMatrix
        ) {
            if (i < fromTopOn) {
                i++;
                partOfOutput.add(a);
            } else {
                i++;
                partOfOutput2.add(a);
            }
        }

        outputMatrixArr.add(partOfOutput);
        outputMatrixArr.add(partOfOutput2);

        return outputMatrixArr;
    }

    public static ArrayList<ArrayList<char[]>> splitVerticallyfromLeftOn(ArrayList<char[]> inputMatrix, int fromLeftOn) {
        ArrayList<ArrayList<char[]>> outputMatrixArr = new ArrayList<>();
        ArrayList<char[]> partOfOutput = new ArrayList<>();
        ArrayList<char[]> partOfOutput2 = new ArrayList<>();
        int i = 0, colNumber = 0;

        for (char a : inputMatrix.get(0)
        ) {
            colNumber++;
        }

        if (fromLeftOn== 0) {
            outputMatrixArr.add(inputMatrix);
            return outputMatrixArr;
        }

        if (fromLeftOn >= colNumber) {
            outputMatrixArr.add(inputMatrix);
            return outputMatrixArr;
        }

        for (char[] a : inputMatrix
        ) {
            partOfOutput.add(Arrays.copyOfRange(a, 0, fromLeftOn));
            partOfOutput2.add(Arrays.copyOfRange(a, fromLeftOn, a.length));
        }

        outputMatrixArr.add(partOfOutput);
        outputMatrixArr.add(partOfOutput2);

        return outputMatrixArr;
    }

    public static void main(String[] args) {

        //выходной список массивов
        ArrayList<ArrayList<char[]>> listOfOutputMatrix = new ArrayList<>();

        //берем тестовый вариант из файла
        ArrayList<char[]> inputMatrix = new ArrayList<>();
        char[] charLine;
        File file = new File("D:\\IDEA projects\\Divide_Matrix\\src\\input.txt");

        //уол-во строк и стролбцов, о-шек
        int colNum = 0, linNum = 0, oNum = 0;

        //считываем наш файл
        try {
            Scanner sc = new Scanner(file);

            if (sc.hasNextLine()) {
                charLine = sc.nextLine().toCharArray();
                colNum = charLine.length;
                linNum++;
                inputMatrix.add(charLine);
            }

            while (sc.hasNextLine()) {
                linNum++;
                inputMatrix.add(sc.nextLine().toCharArray());
            }
        } catch (FileNotFoundException exc) {
            System.out.println("File not found");
        }


        //oNum calculate
        for (char[] a : inputMatrix
        ) {
            for (char b : a
            ) {
                if (b == 'o') {
                    oNum++;
                }
            }
        }

        Iterator<char[]> iterator = inputMatrix.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Columns: " + colNum + ", Lines: " + linNum + ", oNum: " + oNum);

        int inputMatrixS = colNum * linNum;
        int outputSperMatrix = inputMatrixS / oNum;

        System.out.print("Sinput: " + inputMatrixS + " SperMatrix: " + outputSperMatrix);


        System.out.println("\n__________\nВывод нашей дичи:\n");

        /*System.out.println("Проверка vertical раздел на:  \n");
        ArrayList<ArrayList<char[]>> temp = splitVerticallyfromLeftOn(inputMatrix, 5);
        for (ArrayList<char[]> a : temp
        ) {
            for (char[] b : a
            ) {
                System.out.println(b);
            }
            System.out.println();
        }*/

        //когда заведомо нет решений
        if (inputMatrixS % oNum != 0) {
            System.exit(0);
        }

        //само решение
        boolean flag = false;

        do {
            flag = false;
            ArrayList<ArrayList<char[]>> innerOutputList = new ArrayList<>();
            ArrayList<ArrayList<char[]>> tempOutputListBuilder = new ArrayList<>();

            if (listOfOutputMatrix.isEmpty()) {
                innerOutputList.add(inputMatrix);
            } else {
                innerOutputList.addAll(listOfOutputMatrix);
            }

            //если во выходном потоке каждая матрица имеет только 1 онум
            /*if (hasEveryMatrixOnlyOneO(innerOutputList)) {
                for (ArrayList<char[]> a : innerOutputList
                ) {
                    for (char[] b : a
                    ) {
                        System.out.println(b);
                    }
                    System.out.println();
                }
                System.exit(0);
            }*/

            //для каждой матрицы из выходного потока: делим горизонтально (пытаемся до конца строчек), затем если не помогло, вертикально (до конца столбцов)
            for (ArrayList<char[]> nextMatrix : innerOutputList
            ) {

                if (hasThisMatrixOnlyOneO(nextMatrix)) {
                    tempOutputListBuilder.add(nextMatrix);
                    continue;
                }

                ArrayList<ArrayList<char[]>> tempInnerOutputList = new ArrayList<>();
                boolean innerFlag1 = false;
                boolean innerFlag2 = false;
                int innerLinNum, innerColNum = 0;

                innerLinNum = nextMatrix.size();
                for (char a : nextMatrix.get(0)
                ) {
                    innerColNum++;
                }

                //horizontally
                for (int i = 1; i < innerLinNum; i++) {

                    tempInnerOutputList.clear();
                    tempInnerOutputList.addAll(splitHorizontallyfromTopOn(nextMatrix, i));

                    if (hasEveryMatrixCorrectSperO(tempInnerOutputList, outputSperMatrix)) {
                        innerFlag1 = true;
                        tempOutputListBuilder.addAll(tempInnerOutputList);
                        break;
                    }
                    if (innerFlag1) {
                        break;
                    }

                }

                if (innerFlag1) {
                    continue;
                }

                tempInnerOutputList.clear();
                //vertically
                for (int i = 1; i < innerColNum; i++) {
                    tempInnerOutputList.clear();
                    tempInnerOutputList.addAll(splitVerticallyfromLeftOn(nextMatrix, i));

                    if (hasEveryMatrixCorrectSperO(tempInnerOutputList, outputSperMatrix)) {
                        innerFlag2 = true;
                        tempOutputListBuilder.addAll(tempInnerOutputList);
                        break;
                    }

                    if (innerFlag2) {
                        break;
                    }

                }

                if (innerFlag2) {
                    //tempOutputListBuilder.addAll(tempInnerOutputList);
                    continue;
                } else {
                    tempOutputListBuilder.add(nextMatrix);
                    continue;
                }



            }

            //проверяем дало ли что-то разделение. если дало - флаг в true  переписываем выходной поток, если нет - не трогаем флаг и текущий выходной поток
            if (listOfOutputMatrix.size() != tempOutputListBuilder.size()) {
                flag = true;
                listOfOutputMatrix.clear();
                listOfOutputMatrix.addAll(tempOutputListBuilder);
                tempOutputListBuilder.clear();
            }

        } while (flag);

        if (hasEveryMatrixOnlyOneO(listOfOutputMatrix)) {
            for (ArrayList<char[]> a : listOfOutputMatrix
            ) {
                for (char[] b : a
                ) {
                    System.out.println(b);
                }
                System.out.println();
            }
            System.exit(0);
        } else {
            System.out.println("Решений нет мазафака");
        }

    }
}