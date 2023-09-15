import java.util.*;
/*
 * Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например: “Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ

2 - Объем ЖД

3 - Операционная система

4 - Цвет …

Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.
 */
public class Main {
    
    public static ArrayList<Notebook> GenerateListNotebooks(int number) {
        ArrayList<Notebook> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            
            Notebook.OS typeOS = Notebook.OS.get(rand.nextInt(Notebook.OS.SIZE));

            int capacityOZU = Notebook.OptionOZU[rand.nextInt(Notebook.OptionOZU.length)];

            int capacityHDD = Notebook.OptionHDD[rand.nextInt(Notebook.OptionHDD.length)];

            Notebook.Color color = Notebook.Color.get(rand.nextInt(Notebook.Color.SIZE));

            list.add(new Notebook(i, typeOS, capacityOZU, capacityHDD, color));
        }
        return list;
    }

    
    public static void printFilter(Map<Integer, TreeSet<Integer>> map) {
        System.out.println("\nПараметры фильтра:");
        Set<Integer> keyset = map.keySet();
        int code = 1;
        if (keyset.contains(code)) {
            TreeSet<Integer> set = map.get(code);
            System.out.println(String.format("Размер ОЗУ: от %d до %d",
                    set.first(), set.last()));
        } else {
            System.out.println("Размер ОЗУ: все доступные");
        }
        code = 2;
        if (keyset.contains(code)) {
            TreeSet<Integer> set = map.get(code);
            System.out.println(String.format("Объем HDD: от %d до %d",
                    set.first(), set.last()));
        } else {
            System.out.println("Объем HDD: все доступные");
        }
        code = 3;
        if (keyset.contains(code)) {
            TreeSet<Integer> set = map.get(code);
            System.out.printf("Типы операционных систем: ");
            for (int type : set) {
                System.out.printf(Notebook.OS.get(type).toString());
                if (type != set.last())
                    System.out.printf(", ");
            }
            System.out.printf("\n");
        } else {
            System.out.println("Типы операционных систем: все доступные");
        }
        code = 4;
        if (keyset.contains(code)) {
            TreeSet<Integer> set = map.get(code);
            System.out.printf("Цвета: ");
            for (int type : set) {
                System.out.printf(Notebook.Color.get(type).toString());
                if (type != set.last())
                    System.out.printf(", ");
            }
            System.out.printf("\n");
        } else {
            System.out.println("Цвета: все доступные");
        }
    }

    
    public static boolean isMatchNotebook(Notebook notebook, Map<Integer, TreeSet<Integer>> map) {
        Set<Integer> keyset = map.keySet();
        for (int code : keyset) {
            if (code == 1 && map.containsKey(code)) { 
                TreeSet<Integer> set = map.get(code);
                if (notebook.getCapacityOZU() < set.first())
                    return false;
                if (notebook.getCapacityOZU() > set.last())
                    return false;
            }
            if (code == 2 && map.containsKey(code)) { 
                TreeSet<Integer> set = map.get(code);
                if (notebook.getCapacityHDD() < set.first())
                    return false;
                if (notebook.getCapacityHDD() > set.last())
                    return false;
            }
            if (code == 3 && map.containsKey(code)) { 
                TreeSet<Integer> set = map.get(code);
                boolean match = false;
                for (int type : set) {
                    if (Notebook.OS.get(type) == notebook.getTypeOS()) {
                        match = true;
                        break;
                    }
                }
                if (!match)
                    return false;
            }
            if (code == 4 && map.containsKey(code)) {
                TreeSet<Integer> set = map.get(code);
                boolean match = false;
                for (int type : set) {
                    if (Notebook.Color.get(type) == notebook.getColor()) {
                        match = true;
                        break;
                    }
                }
                if (!match)
                    return false;
            }
        }
        return true;
    }

    
    public static ArrayList<Notebook> selectionNotebooks(ArrayList<Notebook> notebooks, Map<Integer, TreeSet<Integer>> map) { 
        ArrayList<Notebook> result = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            if (isMatchNotebook(notebook, map))
                result.add(notebook);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner iScanner = new Scanner(System.in);

        System.out.printf("Введите число ноутбуков в множестве: ");
        ArrayList<Notebook> notebooks = GenerateListNotebooks(iScanner.nextInt());

        System.out.println("Исходное множество ноутбуков: ");
        for (Notebook one : notebooks)
            System.out.println("Notebook: " + one.toString());

        Map<Integer, TreeSet<Integer>> map = new HashMap<>();

        System.out.println("\nРабота с фильтром: ");
        while (true) {
            System.out.println("Введите цифру, соответствующую критерию. Для завершения работы с фильтром введите 0");
            System.out.println("1 - Размер ОЗУ");
            System.out.println("2 - Объем HDD");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            int code = iScanner.nextInt();
            if (code == 0) {
                break;
            } else if (code == 1) {
                map.remove(code);
                TreeSet<Integer> set = new TreeSet<>();
                System.out.println("Введите верхнее и нижнее ограничение памяти ОЗУ, Гб:");
                set.add(iScanner.nextInt());
                set.add(iScanner.nextInt());
                if (set.size() != 2) {
                    System.out.println("Вы ввели одно и то же значение дважды, отмена");
                    continue;
                }
                map.put(code, set);
            } else if (code == 2) {
                map.remove(code);
                TreeSet<Integer> set = new TreeSet<>();
                System.out.println("Введите верхнее и нижнее ограничение объема HDD, Гб:");
                set.add(iScanner.nextInt());
                set.add(iScanner.nextInt());
                if (set.size() != 2) {
                    System.out.println("Вы ввели одно и то же значение дважды, отмена");
                    continue;
                }
                map.put(code, set);
            } else if (code == 3) {
                System.out.println(
                        String.format("Введите код ОС от 0 до %d: (%s)", Notebook.OS.SIZE - 1, Notebook.OS.items()));
                TreeSet<Integer> set = new TreeSet<>();
                if (map.containsKey(code))
                    set = map.get(code);
                int value = iScanner.nextInt();
                if(value < 0 || value >= Notebook.OS.SIZE){
                    System.out.println("Введено значение вне допустимого диапазона");
                    continue;  
                }
                set.add(value);
                map.put(code, set);
            } else if (code == 4) {
                System.out.println(String.format("Введите код цвета от 0 до %d: (%s)", Notebook.Color.SIZE - 1,
                        Notebook.Color.items()));
                TreeSet<Integer> set = new TreeSet<>();
                if (map.containsKey(code))
                    set = map.get(code);
                int value = iScanner.nextInt();
                if(value < 0 || value >= Notebook.Color.SIZE){
                    System.out.println("Введено значение вне допустимого диапазона");
                    continue;  
                }
                set.add(value);
                map.put(code, set);
            } else {
                System.out.println("неверный ввод кода критерия");
                continue;
            }
        }

        printFilter(map);

        ArrayList<Notebook> selected = selectionNotebooks(notebooks, map);
        System.out.println("\nВыборка ноутбуков:");
        for (Notebook one : selected)
            System.out.println("Notebook: " + one.toString());

        System.out.println(String.format("\nПроцент выборки: %f", 100. * selected.size() / notebooks.size()));

        iScanner.close();
    }
}