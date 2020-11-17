package com.maerks;

import com.maerks.entities.Bookings;
import com.maerks.enums.ContainerType;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CassandraRepository<Bookings, Integer> {

    @AllowFiltering
    List<Bookings> findByContainerSizeAndContainerTypeAndOriginAndDestinationAndQuantity(
            Integer containerSize, ContainerType containerType, String origin, String destination, Integer quantity);

    @Query("select max(id) from Bookings")
    Integer findMax();
}