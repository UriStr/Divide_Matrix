import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    //функция горизонталное разделение матрицы на n равных частей
    public static ArrayList<ArrayList<char[]>> splitHorizontally (ArrayList<char[]> inputMatrix,int splitOn) {

        //выходной массив с частями разбитой матрицы, переменная для одной строки
        ArrayList<ArrayList<char[]>> outputMatrixArr = new ArrayList<>();
        char[] lineOfPartOfOutputMatrix;

        //кол-во строк/колонок во входной матрице
        int linesNum, columnsNum, step;
        linesNum = inputMatrix.size();
        columnsNum = inputMatrix.get(0).length;

        //если просят разделить на большее кол-во строк чем сама матрица или нельзя поровну горизонтально поделить вернуть пустой массив
        if ((splitOn > linesNum) || (linesNum % splitOn != 0)) {
            
            return outputMatrixArr;
        }

        //количество строк в каждой из частей после разделения
        step = linesNum / splitOn;

        for (int i = 0; i < linesNum; i = i + step) {

            //переменная для одной части разбиения
            ArrayList<char[]> partOfOutputMatrix = new ArrayList<>();

            for (int j = i; j < i+step; j++) {
                lineOfPartOfOutputMatrix = Arrays.copyOf(inputMatrix.get(j), inputMatrix.get(j).length);
                partOfOutputMatrix.add(lineOfPartOfOutputMatrix);
            }

            //добавляем часть разбиения в выходной массив
            outputMatrixArr.add(partOfOutputMatrix);
        }

        //возвращаем массив матриц
        return outputMatrixArr;
    }

    public static ArrayList<ArrayList<char[]>> splitVertically (ArrayList<char[]> inputMatrix,int splitOn) {

        //выходной массив с частями разбитой матрицы, переменная для одной строки (неполной)
        ArrayList<ArrayList<char[]>> outputMatrixArr = new ArrayList<>();
        char[] lineOfPartOfOutputMatrix;

        //кол-во строк/колонок во входной матрице
        int linesNum, columnsNum, step;
        linesNum = inputMatrix.size();
        columnsNum = inputMatrix.get(0).length;

        //если просят разделить на большее кол-во столбцов чем сама матрица или нельзя поровну вертикально поделить вернуть пустой массив
        if ((splitOn > columnsNum) || (columnsNum%splitOn != 0)) {
            return outputMatrixArr;
        }

        //количество столбцов в каждой из частей после разделения
        step = columnsNum / splitOn;

        for (int i = 0; i < columnsNum; i = i + step) {
            ArrayList<char[]> partOfOutputMatrix = new ArrayList<>();
            for (int j = 0; j < linesNum; j++) {
                lineOfPartOfOutputMatrix = Arrays.copyOfRange(inputMatrix.get(j), i, i + step);
                partOfOutputMatrix.add(lineOfPartOfOutputMatrix);
            }
            outputMatrixArr.add(partOfOutputMatrix);
        }

        //возврат массива матриц
        return outputMatrixArr;
    }

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

    public static boolean hasThisMatrixOnlyOneO(ArrayList<char[]> inputMatrixArr) {
        int oNum = 0, inputArraySize = inputMatrixArr.size();

        for (char[] a : inputMatrixArr
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

    public static boolean hasEveryMatrixEqualAmountOfO(ArrayList<ArrayList<char[]>> inputMatrixArr) {
        int totalONum = 0, inputArraySize = inputMatrixArr.size(), everyMatrixHasContain;

        for (ArrayList<char[]> a : inputMatrixArr
        ) {
            for (char[] b : a
            ) {
                for (char c : b
                ) {
                    if (c == 'o') {
                        totalONum++;
                    }
                }
            }
        }

        if (totalONum % inputArraySize != 0) {
            return false;
        } else {
            everyMatrixHasContain = totalONum / inputArraySize;
        }

        for (ArrayList<char[]> a : inputMatrixArr
        ) { int oInMatrix = 0;
            for (char[] b : a
            ) {
                for (char c : b
                ) {
                    if (c == 'o') {
                        oInMatrix++;
                    }
                }
            }
            if (oInMatrix != everyMatrixHasContain) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        //выходной список массивов
            ArrayList<ArrayList<char[]>> listOfOutputMatrix = new ArrayList<>();

        //берем тестовый вариант из файла
            ArrayList<char[]> inputMatrix = new ArrayList<>();
            char[] charLine;
            File file = new File("C:\\Users\\Uri\\Desktop\\Skill\\Code\\IntelliJ\\Divide Matrix\\src\\input.txt");

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

        int inputMatrixS = colNum*linNum;
        int outputSperMatrix = inputMatrixS/oNum;

        ArrayList<Integer> dividers = new ArrayList<>();

        for (int i = outputSperMatrix; i >= 1; i--) {
            if ((outputSperMatrix % i) == 0) {
                dividers.add(i);
            }
        }

        ArrayList<Integer[]> pairsList = new ArrayList<>();
        ArrayList<Integer[]> addpairList = new ArrayList<>();
        Integer[] onumpair = {oNum, oNum};
        pairsList.add(onumpair);
        for (int i = 0; i < dividers.size()/2; i++) {
            //getPair (12,1), (6, 2) etc...
            int pair1 = dividers.get(0 + i), pair2 = dividers.get(dividers.size() - 1 - i);
            Integer[] pair = {pair1, pair2};
            pairsList.add(pair);
        }

        for (int i = pairsList.size() - 1; i > 0; i--) {
            Integer[] temp = {pairsList.get(i)[1], pairsList.get(i)[0]};
            addpairList.add(temp);
        }

        pairsList.addAll(addpairList);

        Iterator<Integer> iterator2 = dividers.iterator();
        Iterator<Integer[]> iterator3 = pairsList.iterator();



        System.out.print("Sinput: " + inputMatrixS + " SperMatrix: " + outputSperMatrix + " Delimeters: ");
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }

            System.out.print("\npairsList: ");
            while (iterator3.hasNext()) {
                Integer[] temp = iterator3.next();
                System.out.print("{" + temp[0] + ", " + temp[1] + "}, ");
            }


        /*System.out.println("\n\nЩас будет проверка функции горизонтального разделения: ");
        ArrayList<ArrayList<char[]>> checkHorizontalSplit = splitHorizontally(inputMatrix, 2);
        for (ArrayList<char[]> a : checkHorizontalSplit
        ) {
            for (char[] b : a
            ) {
                System.out.println(b);
            }
            System.out.println();
        }
        System.out.println("haseverymatrixonlyone O??:  " + hasEveryMatrixEqualAmountOfO(checkHorizontalSplit));*/

        /*System.out.println("\n\nЩас будет проверка функции вертикального разделения: ");
        ArrayList<ArrayList<char[]>> checkVerticalSplit = splitVertically(inputMatrix, 4);
        for (ArrayList<char[]> a : checkVerticalSplit
        ) {
            for (char[] b : a
            ) {
                System.out.println(b);
            }
            System.out.println();
        }
        System.out.println("haseverymatrixEQUAL o??:  " + hasEveryMatrixEqualAmountOfO(checkVerticalSplit));*/

        /*System.out.println("\n\nЩас проверим  имеет ли эта матрица только один онум?: " + hasThisMatrixOnlyOneO(inputMatrix));*/

        System.out.println("\n__________\nВывод нашей дичи:");

        //Начинается дичь

        //когда заведомо нет решений
        if (inputMatrixS % oNum != 0) {
            System.exit(0);
        }

        //когда границы четные и онум любое
        //сначала пробуем для любых границ и любого кол-во онум
        for (Integer[] nextPair : pairsList
        ) {

            //если во внутреннем входном массиве все матрицы содержат по онум = 1, то выводим эти матрицы и завершаем работу
            if ((!listOfOutputMatrix.isEmpty()) && (hasEveryMatrixOnlyOneO(listOfOutputMatrix))) {
                for (ArrayList<char[]> a : listOfOutputMatrix
                ) {
                    for (char[] b : a
                    ) {
                        System.out.println(b);
                    }
                    System.out.println();
                }
                System.exit(0);
            }

            //нициализируем координаты из пары
            int x = nextPair[0], y = nextPair[1];

            //массив матриц для работы внутри цикла. Если выходной массив метода main пустой - инициализируем массив цикла входной матрицей, если не пустой - содержимым выходного массива метода main.
            ArrayList<ArrayList<char[]>> innerListOfOutputMatrixArr = new ArrayList<>();
            if (listOfOutputMatrix.isEmpty()) {
                //innerListOfOutputMatrixArr.clear();
                innerListOfOutputMatrixArr.add(inputMatrix);
            } else {
                //innerListOfOutputMatrixArr.clear();
                innerListOfOutputMatrixArr.addAll(listOfOutputMatrix);
            }



            //инициализируем темповый массив матриц для результатов разбиения внутреннего входного массива матриц(не забывать очищать!)
            ArrayList<ArrayList<char[]>> tempMatrixList = new ArrayList<>();
            ArrayList<ArrayList<char[]>> tempMatrixListforInnerDividing = new ArrayList<>();
            ArrayList<ArrayList<char[]>> tempMatrixListforInnerDividing2 = new ArrayList<>();


            //пытаемся разбивать матрицы (в которых онум !=1) из внутреннего списка матриц, каждый раз получаемых заново.
            for (ArrayList<char[]> nextMatrixOfListOfOutputMatrix : innerListOfOutputMatrixArr
            ) {
                //для каждой матрицы во входном списке. если она не содержит только 1 онум, то делим ее до тех пор, пока делится либо пока на выходе не будет только по одному онум в матрице
                if (!hasThisMatrixOnlyOneO(nextMatrixOfListOfOutputMatrix)) {

                    tempMatrixListforInnerDividing = splitHorizontally(nextMatrixOfListOfOutputMatrix, x);
                    tempMatrixListforInnerDividing2.addAll(tempMatrixListforInnerDividing);
                    if (!tempMatrixListforInnerDividing.isEmpty() & (hasEveryMatrixEqualAmountOfO(tempMatrixListforInnerDividing))) {
                        while (true) {
                            
                        }

                        tempMatrixList.addAll(tempMatrixListforInnerDividing);
                    }


                    if (!tempMatrixListforInnerDividing.isEmpty()) {
                        tempMatrixList.addAll(tempMatrixListforInnerDividing);
                        continue;
                    }

                    tempMatrixListforInnerDividing.clear();
                    tempMatrixListforInnerDividing2.clear();
                    tempMatrixListforInnerDividing = splitVertically(nextMatrixOfListOfOutputMatrix, y);
                    tempMatrixListforInnerDividing2.addAll(tempMatrixListforInnerDividing);
                    while (!tempMatrixListforInnerDividing2.isEmpty()) {

                    }
                    if (!tempMatrixListforInnerDividing.isEmpty()) {
                        tempMatrixList.addAll(tempMatrixListforInnerDividing);
                        continue;
                    } else {
                        tempMatrixList.add(nextMatrixOfListOfOutputMatrix);
                        continue;
                    }

                } else {
                    tempMatrixList.add(nextMatrixOfListOfOutputMatrix);
                    continue;
                }
            }

        }
            

        //когда границы нечетные и онум нечетное

        //когда границы разнородные и онум без разницы








    }
}
