public class Driver {
    public static void main(String[] args) {
        Farmer person1 = new Farmer("Jeff", true);

        Crops tur = new Turnip();
        Crops c = new Carrot();
        Crops to = new Tomato();
        Crops p = new Potato();

        Crops r = new Rose();
        Crops tul = new Tulip();
        Crops st = new Stargazer();
        Crops su = new Sunflower();

        Crops m = new Mango();
        Crops a = new Apple();
        Crops b = new Banana();
        Crops o = new Orange();

        tur.display();
        c.display();
        to.display();
        p.display();

        r.display();
        tul.display();
        st.display();
        su.display();

        m.display();
        a.display();
        b.display();
        o.display();

        System.out.println();
        person1.addExp(199);
        person1.levelUp();
        System.out.println(person1.getLevel()+" "+person1.getExp());
        person1.addExp(500);
        person1.levelUp();
        System.out.println(person1.getLevel()+" "+person1.getExp());
        person1.levelUp();
        System.out.println(person1.getLevel()+" "+person1.getExp());
        person1.levelUp();
        System.out.println(person1.getLevel()+" "+person1.getExp());

        m.display();
        person1.setLevel(100);
        person1.setMoney(1000);
        person1.upgradeFarmer(3);
        m.display();

        System.out.println();
        Land l = new Land();

        l.display();

    }
}
