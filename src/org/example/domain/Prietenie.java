package org.example.domain;

import java.time.LocalDateTime;


public class Prietenie extends Entity<Tuple<Long,Long>> {

    LocalDateTime date;

    public Prietenie()
    {
        super.setId(new Tuple<Long,Long>((long)0,(long)0));
        date=LocalDateTime.now();
    }

    /**
     *
     * @return the date when the friendship was created
     */
    public LocalDateTime getDate() {
        return date;
    }
}
