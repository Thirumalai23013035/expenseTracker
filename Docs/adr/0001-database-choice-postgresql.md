# ADR 0001: Database Choice — PostgreSQL

## Status
To be Reviewed

## Context
The system is a payment ledger platform requiring:
- Strong consistency
- Transactional guarantees
- Auditable financial records
- Concurrent balance updates

Incorrect balance or transaction ordering would lead to data corruption.

## Decision
We will use PostgreSQL as the primary database for the system.

## Rationale
PostgreSQL provides:
- ACID-compliant transactions
- Row-level locking (`SELECT ... FOR UPDATE`)
- Strong referential integrity
- Mature ecosystem and tooling
- Proven reliability in financial systems

## Alternatives Considered
### MongoDB
- Rejected due to weaker transactional guarantees across documents
- Increased application-level complexity for consistency

### MySQL
- Considered viable
- PostgreSQL chosen for richer constraints and concurrency handling

## Consequences
- Schema-first development
- Ledger operations must be transaction-bound
- Future support for read replicas and analytics

