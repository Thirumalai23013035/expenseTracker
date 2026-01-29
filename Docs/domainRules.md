# General

1. Prefer readable code over clever implementations.
2. Choose variable and method names that are self-explanatory.
3. Business logic must live in Service classes.
   - Utility packages are allowed **only for stateless helper functions**
   - No core business logic in utility classes.
4. If a known edge case or limitation is intentionally not handled:
   - Document it clearly with a comment
   - Prefer adding a TODO with context
   - Do not leave undocumented behavior

# Naming

1. Classes  
   - Use domain language  
   - Examples: `TransactionService`, `AccountService`

2. Methods  
   - Avoid verbs like `handle`, `do`, `process`
   - Method names must describe the behavior of the method
   - Example: `executeP2PTransfer`

3. Variables  
   - No abbreviations like `amt`, `bal`
   - Use full names like `amount`, `balance`

4. Architecture Decision Records (ADR)
   - NNNN-short-decision-name.md
##  

## This project is modular but **not independent**.

Before writing code in any domain:

1. must read:
   - `Docs/architecture.md`
   - `Docs/domainRules.md`
   - The public interfaces of any module that is called

2. If a method made by another dev is called / used:
   - Read its implementation at least once
   - Understand its assumptions and side effects

3. Do NOT:
   - Reimplement logic that already exists in another module / utility
   - Guess how another service works
   - Call internal/private methods across domains

4. If behavior is unclear:
   - Ask before coding
   - Do not “make it work” by assumptions

