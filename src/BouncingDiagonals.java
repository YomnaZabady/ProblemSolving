import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BouncingDiagonals {

    public static void main (String [] args) {
        int[][] arr= new int[][]{{11, 3, 3, 4, 5}, {6, 3, 3, 2, 1}, {12, 2, 4, 4, 5}, {7, 4, 3, 4, 1}, {1, 2, 4, 4, 5}};
        int arrRows = arr.length;
        int[] col0Arr = new int[arrRows];
        int [] row0Sums = new int[arrRows];

        //Hashmap to store the sums with the original indexes of the first column in the matrix
        HashMap<Integer, ArrayList<Integer>> colPositions = new HashMap<>();

        System.out.println("Original Matrix");
        for (int i = 0; i < arrRows; i++) {
            System.out.println(Arrays.toString(arr[i]));
            col0Arr[i] = arr[i][0]; // make a copy of the original values
            int result =sumOfBouncingDiagonals(arr, i);
            row0Sums[i] = result;
            if (colPositions.get(result) != null) {
                ArrayList<Integer> res = colPositions.get(result);
                res.add(i);
                colPositions.put(result, res);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                colPositions.put(result, temp);
            }
        }
        //sum array
        int[] row0SumsKeys = Arrays.stream(colPositions.keySet().toArray()).map(Object::toString).mapToInt(Integer::parseInt).toArray();

        //sorting sum array
        Arrays.sort(row0SumsKeys);

        // changing order of original column according to the summed array

        int orgIndex = 0;
        for (int i = 0; i < row0SumsKeys.length; i++) {
            ArrayList<Integer> index = colPositions.get(row0SumsKeys[i]);
            if (index.size() > 1) {
                //sort based on the original value;
                int [] temp = new int[index.size()];
                for (int j = 0; j < index.size(); j++) {
                    temp[j] = col0Arr[index.get(j)];
                }
                Arrays.sort(temp);
                for (int k = 0; k < temp.length; k++) {
                    arr[orgIndex][0] = temp[k];
                    orgIndex ++;

                }
            } else {
                arr[orgIndex][0] = col0Arr[index.get(0)];
                orgIndex ++;
            }
        }
        System.out.println("Final Matrix:");
        for (int i = 0; i < arrRows; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static int sumOfBouncingDiagonals(int[][] arr, int row) {
        int arrRows = arr.length;
        int arrCols = arr[0].length;
        int col = 0;
        int sum = 0;
        while (col < arrCols) {
            while (row > 0) {
                //diagonally up +cols -rows
                sum+= arr[row][col];
                row -=1;
                col +=1;
            }
            while (row < arrRows && col < arrCols) {
                //diagonally down to the right +col +rows
                sum += arr[row][col];
                row +=1;
                col +=1;
            }
        }
        return sum;
    }
}
