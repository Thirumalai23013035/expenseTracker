# ADR 0002: Ledger Model — Double Entry Accounting

## Status
Proposed

## Context
The system handles monetary transactions including:
- Wallet balances
- Peer-to-peer transfers
- Top-ups and internal transfers

The system must guarantee correctness, auditability, and traceability of all balance changes.

## Decision
We will implement a double-entry ledger model.

Each financial transaction will be represented as a logical transaction
containing multiple ledger entries whose net sum equals zero.

## Rationale
Double-entry accounting:
- Prevents creation or loss of money
- Enables strong auditing and reconciliation
- Simplifies balance verification
- Is the industry standard for financial systems

Balances will be derived from ledger entries rather than directly mutated.

## Alternatives Considered
### Single-entry ledger
- Rejected due to difficulty in enforcing invariants
- Higher risk of balance inconsistencies

### Event-sourced ledger
- Considered too complex for current scope
- Higher implementation and operational overhead

## Consequences
- Ledger tables are append-only
- Balance updates must be transactionally consistent
- Reversals are modeled as compensating transactions
