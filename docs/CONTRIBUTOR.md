# Contributing Guide

Thanks for considering a contribution! This guide explains our workflow, quality gates, and how to get a change merged and released.

## TL;DR
- Branch from `develop` using `feature/<issueId>-<slug>`.
- Use **Conventional Commits**.
- Ensure **Checkstyle**, **tests**, **coverage ≥ 80% (JaCoCo)**, and **PIT** all pass.
- Open a PR to `develop` with a clear description and “Closes #<issueId>”.
- At least **one review** required; prefer **squash merge**.

---

## 1) Prerequisites
- Java **17+**
- Maven **3.8+**
- GitHub account with access to this repo

---

## 2) Branching model (GitFlow‑light)
- `main` — production, tagged releases `vX.Y.Z`
- `develop` — integration branch
- `feature/<issueId>-<short-slug>` — new features/refactors
- `hotfix/<short-slug>` — urgent fixes from `main`
- `release/<X.Y.Z>` — release prep from `develop`

**Examples**
- `feature/42-add-undo-action`
- `hotfix/fix-crash-on-empty-grid`
- `release/1.1.0`

---

## 3) Commit messages (Conventional Commits)
Format: `type(scope): short summary`

Common types:
- `feat:` new feature  
- `fix:` bug fix  
- `docs:` documentation changes  
- `refactor:` code change without behavior change  
- `test:` tests only  
- `chore:` tooling/CI/build housekeeping

**Examples**
- `feat(board): add left-merge movement`
- `fix(ui): correct "You lose!" message`
- `test(controller): cover mergeRight edge cases`

Keep commits focused and small.

---

## 4) Issues → Pull Requests
1. **Open an issue** using the right template (🐛 bug, 🧩 feature, 📝 docs).
2. **Create a branch** from `develop`: `feature/<issueId>-<slug>`.
3. **Implement** with tests and docs updates if needed.
4. **Run locally**:
   ```bash
   mvn -q verify                                    # build + tests + JaCoCo report (≥ 80% gate)
   mvn -q org.pitest:pitest-maven:mutationCoverage  # optional local PIT
   ```
5. **Open a PR** to `develop`:
   - Title with Conventional Commit style.
   - Description with context and **“Closes #<issueId>”**.
   - Fill the PR checklist (tests, screenshots if UI).

**Review & merge**
- ≥ 1 reviewer approval.
- Prefer **squash merge**.
- Keep PRs small; large PRs slow down review.

---

## 5) Quality gates (must pass)
- **Checkstyle**: no violations in new/changed code.
- **Unit tests**: stable, deterministic; no external network/FS.
- **Coverage gate**: **JaCoCo ≥ 80%** (build fails below).
- **Mutation testing**: **PIT** workflow must pass.

**CI workflows (names must match)**
- `CI` — build, tests, coverage, JAR artifact
- `Checkstyle (lint)` — static analysis
- `Mutation Testing (PIT)` — mutation coverage
- `Release` — on tag `v*.*.*`, builds and attaches the JAR
- `Telemetry` — aggregates workflow duration & status

---

## 6) Code style & structure
- Target **Java 17**; no preview features.
- Avoid `System.out.println` in production code — use a logger.
- Keep methods short; avoid long parameter lists and deep nesting.
- Separate UI (Swing) from logic; make logic testable without UI.

**Project layout (high level)**
```
src/
  main/java/be/unamur/game2048/...
  test/java/be/unamur/game2048/...
.github/workflows/...
docs/
```

---

## 7) Testing guidelines
- One test class per public class when meaningful.
- Names: `should<DoX>When<Y>()`.
- Cover edge cases (empty/full grid, single/chain merges).
- Avoid flakiness (time, randomness) — inject seeds if needed.

---

## 8) Labels & triage
- `needs triage` (default on new issues)
- `bug`, `enhancement`, `documentation`, `good first issue`, `help wanted`
- Use milestones for releases.

---

## 9) Security & responsible disclosure
If you find a vulnerability, please **do not open a public issue**.
Email: **semy.drif@student.unamur.be**. We’ll acknowledge within 72h.

---

## 10) Releasing
- Create/merge a **release branch** into `main` after CI is green.
- Tag: `vMAJOR.MINOR.PATCH` (SemVer).
- `Release` workflow publishes a GitHub Release with the JAR attached.
- Summarize changes (notable PRs, breaking changes).

---

## 11) Code of Conduct
This project abides by the **Contributor Covenant**. Please read `CODE_OF_CONDUCT.md` before contributing.
