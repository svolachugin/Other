import java.util.Scanner;
import java.lang.NumberFormatException;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Яблоки", "Молоко", "Яйца", "Колбаса"};
        String[] productsAction = {"Мороженое", "Печенье", "Кофе"};//товары по акции
        int[] prices = {100, 200, 300, 150, 400};//ценник на товары без акции
        int[] pricesAction = {100, 350, 500};// ценник на товары по акции
        int[] marcetProduct = new int[prices.length + pricesAction.length];

        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
        System.out.println("Список товаров по акции 3=2");
        for (int j = 0; j < productsAction.length; j++) {
            System.out.println((j + products.length + 1) + ". " + productsAction[j] + " " + pricesAction[j] + " руб/шт");
        }
        int sumProducts = 0;
        while (true) {
            int productNumber = 0;
            int productCount = 0;
            System.out.println("Выберите товар и количество или введите `end`");
            try {
                String inputString = scanner.nextLine(); // Считываем номер операции
                if (inputString.equals("end")) {
                    break;
                }
                String[] inputProduct = inputString.split(" ");
                if (inputProduct.length == 2) {//проверка условия ввода двух чисел через пробел
                    productNumber = Integer.parseInt(inputProduct[0]) - 1;
                    productCount = Integer.parseInt(inputProduct[1]);
                } else {
                    System.out.println("Введите два числа через пробел!");
                }
                if (productNumber + 1 > marcetProduct.length || ((productNumber + 1) < 0)) { //проверка условия корректности ввода двух чисел больше 0 и не больше длины массива
                    System.out.println("Выберите продукт от 1 до " + marcetProduct.length);
                } else {
                    if (productCount == 0) {
                        marcetProduct[productNumber] = 0;
                    } else if ((marcetProduct[productNumber] + productCount) >= 0) {
                        marcetProduct[productNumber] += productCount; // заполняем массив с кол-вом купленных продуктов
                    } else {
                        System.out.println("Ошибка операции!");
                        System.out.println("Количество товаров в корзине - " + marcetProduct[productNumber] +
                                ", а Вы пытаетесь убрать из нее - " + (productCount * -1));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА! Вы ввели текст вместо числа");
            }
        }
        System.out.println("Ваша корзина:");
        System.out.println();
        int sumProduct;
        int sumProductAction = 0;
        int sumProductActionAll = 0;
        System.out.println("Товары без акции");
        for (int i = 0; i < marcetProduct.length; i++) {
            if (marcetProduct[i] > 0) {
                if (marcetProduct[i] / 3 == 0 && i > prices.length - 1) {//если акционных товаров менее 3 то скидка не действует!!!
                    sumProduct = marcetProduct[i] * pricesAction[i - products.length];
                    System.out.println(productsAction[i - products.length] + " " + marcetProduct[i] + " шт " + pricesAction[i - products.length] + " руб/шт " + sumProduct + " руб в сумме");
                    sumProducts += sumProduct;
                } else if (i < 5) {
                    sumProduct = marcetProduct[i] * prices[i];
                    System.out.println(products[i] + " " + marcetProduct[i] + " шт " + prices[i] + " руб/шт " + sumProduct + " руб в сумме");
                    sumProducts += sumProduct;
                }
            }
        }
        System.out.println("Итого: " + sumProducts + " руб"); // без акции
        System.out.println();

        System.out.println("Товары по акции 3 по цене 2: ");
        for (int i = 0; i < marcetProduct.length; i++) {
            if (marcetProduct[i] != 0) {
                if (marcetProduct[i] / 3 > 0 && i > prices.length - 1) {//проверяю корзину на акционные товары
                    int numberProductsAction = i - products.length;
                    int countProductsAction = (marcetProduct[i] * 2 / 3 + marcetProduct[i] % 3);
                    sumProductAction = countProductsAction * pricesAction[numberProductsAction];
                    System.out.println(productsAction[numberProductsAction] + " " + marcetProduct[i] + " шт вместо " + countProductsAction + " шт " + pricesAction[numberProductsAction] + " руб/шт в сумме " + sumProductAction + " руб.");
                    sumProductActionAll += sumProductAction;
                }
            }
        }
        System.out.println("Итого по акции: " + sumProductActionAll + " руб");
        System.out.println();
        System.out.println("Итого по всем: " + (sumProducts + sumProductAction) + " руб");
    }
}