package mysql.lecture;

import javax.persistence.*;

@Entity
@Table(name = "test.magic")
public class Magic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "defence")
    private int defence;
    @Column(name = "attack")
    private int attBonus;
    @Column(name = "damage")
    private int damage;

    public Magic(String name, int defence, int attBonus, int damage) {
        this.name = name;
        this.defence = defence;
        this.attBonus = attBonus;
        this.damage = damage;
    }
    public Magic() {}

    @Override
    public String toString() {
        return "Magic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", defence=" + defence +
                ", attBonus=" + attBonus +
                ", damage=" + damage +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setAttBonus(int attBonus) {
        this.attBonus = attBonus;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
