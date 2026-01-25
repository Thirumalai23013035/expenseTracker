## Ledger Invariants
- Every transaction must have >= 2 ledger entries
- Sum of ledger entries per transaction must equal zero
- Ledger entries are immutable
- All entries of a transaction are committed atomically

## Domain Definitions
- Account: owns a balance
- Transaction: logical grouping of ledger entries
- Ledger Entry: debit or credit against an account

## Prohibited Operations
- Updating ledger entries
- Direct balance mutation outside a transaction

## Rules for Ledger Access

- The `ledger` package is the single source of truth for all financial state changes.
- Only `LedgerService` is permitted to perform write operations on:
  - ledger entries
  - transactions
  - balances

- All other components (controllers, services, jobs, listeners) must interact with the ledger
  exclusively through public methods exposed by `LedgerService`.

- Direct database access, repository usage, or balance mutation outside the `ledger` package
  is considered to be bad code discipline.
