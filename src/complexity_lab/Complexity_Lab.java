/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexity_lab;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jesus David
 */
public class Complexity_Lab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite número de registros");
        int m = s.nextInt();
        System.out.println("Digite número de campos (adicionales al campo llave)");
        int n = s.nextInt();
        int i = 0;
        Object[][] mat = new Object[m][n + 1];
        
        BigInteger big, ten = BigInteger.TEN, one = BigInteger.ONE;

        for (int j = 1; j <= n; j++) {
            System.out.println("➥ Campo " + j);
            System.out.println("↳ Digite tipo de campo; 0 = Numérico, 1 = Alfanumérico");
            int type = s.nextInt();
            System.out.println("↳ Digite longitud");
            int len = s.nextInt();
            switch (type) {
                case 0:
                    for (int k = 0; k < m; k++) {
                        mat[k][j] = GenRandom(String.valueOf(one.multiply(ten.pow(len)).subtract(one)), String.valueOf(one.multiply(ten.pow(len - 1))));
                    }
                    
                    break;
                case 1:  ;
                    for (int k = 0; k < m; k++) {
                        mat[k][j] = generateRandomText(len).substring(0, len);
                    }
                    
                    break;
                default:
                    System.out.println("Tipo inválido, se tomará como numérico");
                    type = 0;
                    for (int k = 0; k < m; k++) {
                        mat[k][j] = GenRandom(String.valueOf(one.multiply(ten.pow(len)).subtract(one)), String.valueOf(one.multiply(ten.pow(len - 1))));
                    }
                    
                    break;
            }
        }

        mat[i][0] = GenRandom(String.valueOf(one.multiply(ten.pow(50)).subtract(one)), String.valueOf(one.multiply(ten.pow(49))));
        ShowRow(mat, i, n);
        i += 1;
        while (i < m) { //Direccionamiento de llaves
            big = GenRandom(String.valueOf(one.multiply(ten.pow(50)).subtract(one)), String.valueOf(one.multiply(ten.pow(49))));
            if (!Exists(mat, big, i)) {
                mat[i][0] = big;
                ShowRow(mat, i, n);
                i += 1;
            }
        }

        Sort(mat, m, n);
        System.out.println("Matriz Ordenada");
        for (int t = 0; t < m; t++) {
            ShowRow(mat, t, n);
        }
//        for (int j = 0; j < l; j++) {
//            System.out.print(types[j]+" ");
//        }
//        System.out.println("");
        boolean sw = false;
        while (sw == false) {
            System.out.println("Digite número de campo para buscar (0 = Campo clave)");
            int num = s.nextInt();
            if (num <= n) {
                sw = true;
                System.out.println("Digite dato a buscar");
                Scanner ss = new Scanner(System.in);
                String dat = ss.nextLine();
                Search(dat, mat, num);
            } else {
                System.out.println("☹ Campo inválido, intente de nuevo");;
            }

        }

    }

    public static void Search(String dat, Object[][] mat, int num) {

        boolean sw = false;
        for (int j = 0; j < mat.length; j++) {
            if (dat.equals(String.valueOf(mat[j][num]))) {
                sw = true;
                if (num == 0) {
                    System.out.println("«Llave encontrada»");
                } else {
                    System.out.println("Dato encontrado en el registro: " + mat[j][0]);
                }
                System.out.println("⇝Registro: ");
                ShowRow(mat, j, mat[j].length - 1);
                break;
            }
        }
        if (sw == false) {
            System.out.println("Dato no encontrado");

        }

    }

    public static void Sort(Object[][] mat, int m, int n) {
        Object aux;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 1; j < m; j++) {
                BigInteger a = new BigInteger(String.valueOf(mat[i][0]));
                BigInteger b = new BigInteger(String.valueOf(mat[j][0]));
                if (a.compareTo(b) > 0) {
                    for (int k = 0; k <= n; k++) {
                        aux = mat[i][k];
                        mat[i][k] = mat[j][k];
                        mat[j][k] = aux;
                    }
                }
            }
        }

    }

    public static void ShowRow(Object[][] mat, int i, int n) {
        System.out.print("」");
        for (int j = 0; j <= n; j++) {
            System.out.print(mat[i][j] + " ┊ ");
        }
        System.out.println("");
    }

    public static String generateRandomText(int len) {
        SecureRandom random = new SecureRandom();
        String text = new BigInteger(len * 6, random).toString(32);
        return text;
    }

    public static BigInteger GenRandom(String up, String low) {
        BigInteger bigInteger = new BigInteger(up);// uper limit
        BigInteger min = new BigInteger(low);// lower limit
        BigInteger bigInteger1 = bigInteger.subtract(min);
        Random rnd = new Random();
        int maxNumBitLength = bigInteger.bitLength();
        BigInteger aRandomBigInt;
        aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
        if (aRandomBigInt.compareTo(min) < 0) {
            aRandomBigInt = aRandomBigInt.add(min);
        }
        if (aRandomBigInt.compareTo(bigInteger) >= 0) {
            aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);
        }

        return aRandomBigInt;

    }

    public static boolean Exists(Object[][] mat, BigInteger num, int i) {
        boolean sw = false;
        for (int j = 0; j < i; j++) {
            BigInteger aux = new BigInteger(String.valueOf(mat[j][0]));
            if (aux.compareTo(num) == 0) {
                sw = true;
            }
        }
        return sw;
    }

}
