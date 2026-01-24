# Architecture Overview

## High-Level Component Diagram:
```text
+--------------+      +------------+       +----------+
|              |----> |            |<------|          |
| Transaction  |      |   Ledger   |       | Account  |
|              |----> |            |       |          |
+--------------+      +------------+       +----------+
```

## Dependency Direction:
1. **Transaction → Ledger**: The transaction component records and stores details such as incoming payments or outgoing transfers. These transactions feed into the ledger system, serving as the source of truth for all movement of money.
2. **Transaction → Account**: Transactions are directly tied to the account system to register deposits, withdrawals, or updates related to the user's account lifecycle.
3. **Ledger ✗→ Account**: The ledger operates independently of the account system and does not directly interact with it. It acts solely as a robust and immutable repository.

## Key Principles:
1. **Single Database, Synchronous Processing**:
    - The entire system operates from one single database to maintain data consistency and ensure that all processes function within the same transactional boundaries.
    - All operations are synchronous, meaning that actions (e.g., processing payments, updating ledgers, or computing balances) happen in a sequence to guarantee reliability.

2. **The Purpose of the Ledger**:
    - The ledger functions as the absolute source of truth for all monetary movements. It maintains a complete and immutable history of all the transactions, ensuring both transparency and auditability.
    - This separation provides flexibility in reconstructing history or tracing down discrepancies in a secure and verifiable manner.

3. **Why Account Balance is a Projection**:
    - The account balance isn’t stored or calculated directly but is instead derived as a projection based on the transaction data.
    - This ensures that the account balance accurately reflects the current state, even after adjustments or corrections in past transactions are made.

   - Relying on transactions as the source of truth eliminates the risk of storing inconsistent balances in the system while ensuring reconciliation is straightforward and precise.