package com.develhope.spring.rent;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Rents")
@AllArgsConstructor
@NoArgsConstructor
public class RentInfo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long rentId;

        private String startDate; // Data inizio noleggio
        private String endDate; // Data fine noleggio
        private Double dailyCost; // Costo giornaliero noleggio
        private Double totalCost; // Costo totale noleggio
        private Boolean isPaid; // Flag pagato

        @ManyToOne
        @JoinColumn(name = "vehicle_id")
        private Vehicle vehicle;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Users customer;

        @ManyToOne
        @JoinColumn(name = "seller_id")
        private Users seller;

        // TODO totalCost should be calculated by the program, not the user

        /*
         * public RentInfo(Double totalCost) {
         * this.totalCost = calculateTotalCost(dailyCost, startDate, endDate);
         * }
         * 
         * private Double calculateTotalCost(Double dailyCost, OffsetDateTime startDate,
         * OffsetDateTime endDate){
         * Double rentHourlyCost = dailyCost/24;
         * Duration timeDifference = Duration.between(startDate, endDate);
         * Long rentTimeInHours = timeDifference.toHours(); //Payment is calculated
         * based on whole hours of rent time
         * return rentHourlyCost * rentTimeInHours;
         * }
         */
}