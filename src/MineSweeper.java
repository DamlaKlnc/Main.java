import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    Scanner input = new Scanner(System.in);
    Random rndm = new Random();
    int row, colum, intput, x, y;
    boolean inpx = true, inpy = true, isWin = true;


    MineSweeper() {
        enterGame();
        String[][] mineMap = new String[this.row][this.colum];
        String[][] mineFarm = new String[this.row][this.colum];
        mineMapFarm(mineMap);
        mineMapFarm(mineFarm);
        mineMapCreate(mineMap);
        printMap(mineMap);
        while (this.isWin) {
            isMineControl(mineMap);
            this.isWin = isWin(mineMap, mineFarm, this.x, this.y);
        }
    }

    void enterGame() {
        System.out.println("========MAYIN TARLASI OYUNUNA HOŞGELDİNİZ======");
        System.out.print("Oluşturmak istediğiniz oyun alanının satır sayısını belirleyin: ");
        int row = input.nextInt();
        this.row = row;
        System.out.print("Oluşturmak istediğiniz oyun alanının sütun sayısını belirleyin: ");
        int colum = input.nextInt();
        this.colum = colum;
    }

    void mineMapFarm(String[][] mine) {//KAPALI HARİTA OLUŞUMU
        for (int i = 0; i < mine.length; i++) {
            for (int j = 0; j < mine[i].length; j++) {
                mine[i][j] = " - ";
            }
        }
    }

    int randomx() {
        return rndm.nextInt(this.row);
    }

    int randomy() {
        return rndm.nextInt(this.colum);
    }

    int mineCount() {
        return this.colum * this.row / 4;
    } //Mayın sayısı

    void mineMapCreate(String[][] mineMap) {//Mayınların oluşturulması başarılı
        int count = mineCount();
        while (count > 0) {
            mineMap[randomx()][randomy()] = " * ";
            count--;
        }
    }

    void printMap(String[][] mineMap) {
        for (String[] a : mineMap) {
            for (String b : a) {
                System.out.print(b);
            }
            System.out.println();
        }
    }

    int intputx() {
        System.out.print("Satır sayısını giriniz: ");
        this.intput = input.nextInt() - 1;
        return intput;


    }//kullanıcı satır girişi;

    int intputy() {
        this.inpy = true;
        System.out.print("Sütun sayısını giriniz: ");
        this.intput = input.nextInt() - 1;
        return intput;
    }

    void isMineControl(String[][] mineMap) {
        int x = intputx();
        int y = intputy();
        if (isIn(x, y)) {
            this.x = x;
            this.y = y;
        } else {
            System.out.println("Geçersiz koordinat girişi yaptınız.");
            isMineControl(mineMap);
        }
    }

    boolean isIn(int x, int y) {
        if (!(0 <= x && x < this.row)) {
            return false;
        }
        if (!(0 <= y && y < this.colum)) {
            return false;
        }
        return true;
    }

    int countMine(String[][] mineMap, int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isIn(i, j)) {
                    if (mineMap[i][j].equals(" * ")) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    boolean isWin(String[][] mineMap, String[][] mineFarm, int a, int b) {
        if (mineMap[a][b].equals(" * ")) {
            System.out.println("Game Over\n ======OYUN SONU======");
            return false;
        } else if (mineMap[a][b].equals(" - ")) {
            mineFarm[a][b] = " " + String.valueOf(countMine(mineMap, a, b)) + " ";
            mineMap[a][b] = " " + String.valueOf(countMine(mineMap, a, b)) + " ";
            if (!isCleanArea(mineMap)) {
                printMap(mineFarm);
                return true;
            } else {
                System.out.println("Tebrikler Kazandınız.\n ======OYUN SONU======");
                return false;
            }

        }
        System.out.println("Seçtiğiniz koordinat daha önce seçilmiştir.");
        return true;
    }

    boolean isCleanArea(String[][] mineMap) {
        int count = 0;
        for (String[] a : mineMap) {
            for (String b : a) {
                if (b.equals(" - ")) {
                    count++;
                }
            }
        }
        if (count == 0) return true;
        return false;
    }
}





