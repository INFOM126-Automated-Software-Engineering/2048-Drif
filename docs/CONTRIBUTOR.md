# Contributing Guide

Thanks for considering a contribution! This guide explains our workflow, quality gates, and how to get a change merged and released.

## TL;DR
- Branch from `develop` using `feature/<issueId>-<slug>`.
- Use **Conventional Commits**.
- Ensure **Checkstyle**, **tests**, **coverage ≥ 80% (JaCoCo)**, and **PIT** all pass.
- Open a PR to `develop` with a clear description and “Closes #<issueId>”.
- At least **one review** required; prefer **squash merge**.
- **(New)** Follow the **branch protection rules** (see §12) and keep PR titles semantic (see §3.2).

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

### 3.1) Commit template (recommended)
Add a local template to streamline Conventional Commits:
```bash
git config commit.template .gitmessage.txt
```
Suggested `.gitmessage.txt` (add to repo root):
```text
<type>(<scope>): <short summary>

# Why / What / How
# Breaking changes (if any)

Closes #<issueId>
```

### 3.2) Enforce semantic PR titles (optional but recommended)
Add a lightweight check so PR titles follow Conventional Commits. Create `.github/workflows/semantic-pr.yml`:
```yaml
name: Semantic PR Title
on:
  pull_request:
    types: [opened, edited, synchronize]
jobs:
  check:
    runs-on: ubuntu-latest
    steps:
      - uses: amannn/action-semantic-pull-request@v5
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```
This fails the PR if the title isn’t semantic (e.g., `feat: …`, `fix: …`).

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
   - Title with Conventional Commit style (see §3 / §3.2).
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

---

## 12) Branch protection policy (for `main` and `develop`)
Configure in **Settings → Code and automation → Branches → Branch protection rules**:
1. **Require a pull request before merging** (block direct pushes).
2. **Require at least 1 approving review**.
3. **Dismiss stale reviews** when new commits are pushed.
4. **Require status checks to pass**:
   - `CI`
   - `Checkstyle (lint)`
   - `Mutation Testing (PIT)`
5. **Require branches to be up to date** before merging.
6. **Restrict who can push to matching branches** (maintainers only).
7. **Include administrators** (enforce for admins).

> Link this section in onboarding docs and PR templates so contributors know what must be green before merge.

---

## 13) (Optional) Automations to go above expectations
- **Semantic PR titles**: see §3.2 for the `semantic-pull-request` check.
- **Commit template**: see §3.1 to standardize messages.
- **Discussions**: for questions/ideas use the channel → https://github.com/INFOM126-Automated-Software-Engineering/2048-Drif/discussions/new/choose

