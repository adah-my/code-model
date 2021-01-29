//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model.serialization.pojo;

public class ZSetBean implements Comparable<ZSetBean> {
    private String key;
    private Long score;

    public ZSetBean() {
    }

    public String getKey() {
        return this.key;
    }

    public ZSetBean setKey(String key) {
        this.key = key;
        return this;
    }

    public Long getScore() {
        return this.score;
    }

    public ZSetBean setScore(Long score) {
        this.score = score;
        return this;
    }

    @Override
    public int compareTo(ZSetBean o) {
        return Long.valueOf(this.getScore() - o.getScore()).intValue();
    }

    @Override
    public int hashCode() {
        int result = 31 * 1 + (this.key == null ? 0 : this.key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            ZSetBean other = (ZSetBean)obj;
            if (this.key == null) {
                if (other.key != null) {
                    return false;
                }
            } else if (!this.key.equals(other.key)) {
                return false;
            }

            return true;
        }
    }

    @Override
    public String toString() {
        return "ZSetBean [key=" + this.key + ", score=" + this.score + "]";
    }
}
