HIIII ANUUUU AND KEERTHANA!!!!

# Payment Ledger System

Backend-first payment system built around an append-only, double-entry ledger.

The project focuses on correctness of money movement, transactional safety, and auditability under concurrent operations.

## High-level Idea
- Users can create accounts and perform money operations (add, withdraw, transfer)
- All money movement is recorded via a double-entry ledger
- Ledger entries are append-only and immutable
- Balances are derived from ledger data, not updated directly

## Core Design Principles
- Correctness over features in the payment system
- Idempotent and retry-safe money operations
- Clear transaction boundaries
- Backend-heavy architecture with minimal transactional UI

## Expense Tracker & Analytics
In addition to the payment system, the project includes a personal expense tracking and analytics layer.

- Expense data is derived from ledger records
- Operates on read-only, aggregated data
- Provides dashboards, charts, and spending insights per user
- Does not modify or interfere with core payment logic

## Architecture Notes
- Ledger is the single source of truth for money
- Expense tracking is isolated from transaction processing
- Frontend communicates with backend strictly via APIs
