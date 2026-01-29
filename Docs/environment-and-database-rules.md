# Environment and Database Rules

## Local Development Database

- Each dev must run their own local database instance.
- No shared database instances are practically possible for development.

- The local database **must use the same database name and schema**
  as defined in application configuration (e.g., `ledger`).

- Application startup with the `local` profile assumes the presence of
  a correctly initialized local database. Failure to start without the
  database is expected behavior.

## Schema Ownership and Changes

- Any creation, modification, or deletion of database schema
  (tables, columns, constraints, indexes) must be explicitly documented.

- Schema changes must be recorded in one of the following:
  - SQL migration files (preferred, when introduced), or
  - Architecture / database documentation under `docs/`

- Silent or undocumented schema changes are prohibited.

## Rationale

These rules exist to:
- Ensure consistent behavior during work among each of us
- Prevent schema drift
- Preserve ledger correctness and auditability
