package com.maerks.entities;

import com.maerks.enums.ContainerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Bookings {

//    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
//    @CassandraType(type = CassandraType.Name.TIMEUUID)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @CassandraType(type = CassandraType.Name.INT)
    @Column("id")
    private Integer id;
    @Column("container_type")
    private ContainerType containerType;
    @Column("container_size")
    private Integer containerSize;
    @Column("origin")
    private String origin;
    @Column("destination")
    private String destination;
    @Column("quantity")
    private Integer quantity;
    @Column("timestamp")
    private Timestamp timestamp;
}
