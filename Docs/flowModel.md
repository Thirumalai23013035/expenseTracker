 Flow Models for Payment Ledger

This document outlines the various flows for handling transactions and operations in the Payment Ledger system. The following flows are detailed:

1. P2P Transfer (Happy Path, MVP)
2. Account Creation (Demo Initialization)

---

## 1. IDEAL P2P TRANSFER — HAPPY PATH (END-TO-END)

### Assumptions:

- **Sender A**: accountId = `A1`
- **Receiver B**: accountId = `B1`
- **Amount**: ₹100
- **Transaction ID**: `tx-123`

### Step-by-step Flow:

### STEP 0: Request Enters the System
**Component**: TransactionController

- **Input**:
  ```json
  {
    "fromAccount": "A1",
    "toAccount": "B1",
    "amount": 100
  }
  ```
- Controller forwards the request downstream.

### STEP 1: Transaction Intent is Created
**Component**: TransactionService

- **Actions**:
  - Generate `transactionId`
  - Persist transaction:
    ```text
    id = tx-123
    type = P2P
    status = INIT
    from = A1
    to = B1
    amount = 100
    ```

- This is the source intent. Everything else is built from this.

### STEP 2: Validate Business Rules
**Component**: TransactionService

- **Checks**:
  - `A1 ≠ B1`
  - `Amount > 0`
  - `Sender balance ≥ 100`
  - `Sender & Receiver are ACTIVE`

- **Note**: Balance check is from the account table, NOT the ledger.
- **Failure**: If any check fails → mark transaction as `FAILED` and exit.

### STEP 3: Begin Database Transaction (CRITICAL)
**Component**: Spring `@Transactional`

- From this point:
  - Either everything commits.
  - Or everything rolls back (no partial state allowed).

### STEP 4: Write to the Ledger (IMMUTABLE FACT)
**Component**: LedgerService

- **Action**:
  Write two rows in the ledger:
  ```text
  account   | type   | amount | txId
  ----------|--------|--------|-------
  A1        | DEBIT  | 100    | tx-123
  B1        | CREDIT | 100    | tx-123
  ```

- **Rules Enforced**:
  - Double-entry accounting.
  - Consistent `transactionId`.
  - Idempotency checks.

### STEP 5: Update Account Balances (PROJECTION)
**Component**: AccountService

- **Actions**:
  - `A1.balance -= 100`
  - `B1.balance += 100`

- **Notes**:
  - No ledger reads.
  - No recalculations. Just apply the known intent.

### STEP 6: Mark Transaction as SUCCESS
**Component**: TransactionService

- Update transaction status to `SUCCESS`.

### STEP 7: Commit DB Transaction
**Component**: Spring `@Transactional`

- Persist all changes atomically.
- Client receives HTTP `200 OK`.

---

## 2. ACCOUNT CREATION (DEMO INITIALIZATION)

### Assumptions:

- **When?**: A user creates a new account in the system.
- **Balance Initialization**: Every new account starts with an initial balance of ₹10,000 (for demo purposes).

### Step-by-Step Flow:

### STEP 0: Request Enters the System
**Component**: AccountController

- **Input**:
  ```json
  {
    "accountHolder": "John Doe",
    "accountType": "Savings"
  }
  ```
- Controller forwards the request downstream.

### STEP 1: Create Account
**Component**: AccountService

- **Actions**:
  - Generate `accountId` (e.g., `A1`).
  - Persist account details:
    ```text
    accountId = A1
    holderName = John Doe
    type = Savings
    balance = 10,000
    status = ACTIVE
    ```

- **Why?**: Establish the user account with an initial demo balance.

### STEP 2: Return Success Response
**Component**: AccountService

- **Response**:
  ```json
  {
    "accountId": "A1",
    "initialBalance": 10000,
    "status": "ACTIVE"
  }
  ```
- Client receives HTTP `201 Created`.

---

### Notes:
- These processes ensure atomicity and data consistency across the system.
- The specific components and steps are designed around the principles of simplicity and extensibility for further enhancements.
