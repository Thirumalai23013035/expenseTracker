package com.example.paymentledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * NOTE:
 * This application uses Spring Profiles to control environment behavior.
 * - `dev` profile:
 *   Used for local development without PostgreSQL.
 *   Uses in-memory database (H2).
 * - `local` profile:
 *   Requires local PostgreSQL with the expected schema.
 *   Used for validating ledger and database integrity.
 * Choose profile at runtime using:
 *   -Dspring.profiles.active=dev
 *   -Dspring.profiles.active=local
 * Do NOT hardcode active profiles in application.yml.
 * If u wish to run on local profile change active: local in application.yml
 * If u wish to run on dev profile change active: dev in application.yml
 * Before pushing code to the repo make sure to revert back any changes u made to active parameter in application.yml
 * This is not recommended, but since we are just starting it is fine to make changes to .yml files
 * So make sure u guys always revert changes u made to properties.yml
 * I'd recommend to once learn about yml files and about profile configurations.
 */


@SpringBootApplication
public class PaymentLedgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentLedgerApplication.class, args);
    }

}
