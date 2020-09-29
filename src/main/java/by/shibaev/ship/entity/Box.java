package by.shibaev.ship.entity;

public class Box extends Entity{
    private int volume;
    private String name;

    public Box() {
        this.volume = 1;
        this.name = "Unknown";
    }

    public Box(int volume) {
        this.volume = volume;
        this.name = "Unknown";
    }

    public Box(int volume, String name) {
        this.volume = volume;
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Box box = (Box) o;

        if (volume != box.volume) return false;
        return name != null ? name.equals(box.name) : box.name == null;
    }

    @Override
    public int hashCode() {
        int result = volume;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Box{");
        sb.append("volume=").append(volume);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
