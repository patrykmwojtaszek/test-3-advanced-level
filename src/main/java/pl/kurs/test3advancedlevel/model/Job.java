package pl.kurs.test3advancedlevel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "jobs")
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Long id;

    private String uuid;

    @Column(nullable = false)
    private Character letter;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int delay;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus jobStatus;

    public Job() {
    }

     public Job(Character letter, int quantity, int delay) {
        this.letter = letter;
        this.quantity = quantity;
        this.delay = delay;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int dely) {
        this.delay = dely;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", letter=" + letter +
                ", quantity=" + quantity +
                ", delay=" + delay +
                ", jobStatus=" + jobStatus +
                '}';
    }
}
