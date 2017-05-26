package ohtu;

public class Submission {
    Integer viikko;
    Integer tunnit;
    Integer max = 0;
    Integer yht = 0;
    String teht = null;
    String kurssi;
    Boolean a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21;
    private String student_number;

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public void yhteensa() {
        this.teht = "";
        for (int i = 1; i <= 21; i++) {
            try {
                if (this.getClass().getDeclaredField("a" + i).get(this) == null) {
                    continue;
                } else if ((Boolean) this.getClass().getDeclaredField("a" + i).get(this)) {
                    this.max++;
                    this.yht++;
                    teht += i + " ";
                } else {
                    max++;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }
    }

    @Override
    public String toString() {
        return "Viikko " + this.viikko + ":  tehtyjä tehtäviä yhteensä: " + this.yht + " (maksimi " +
                this.max + "), aikaa kului " + this.tunnit + " tuntia, tehdyt tehtävät: " + teht;
    }

}
