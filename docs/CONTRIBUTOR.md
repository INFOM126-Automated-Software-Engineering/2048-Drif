# Contributing Guide

Thanks for considering a contribution! This guide reflects the current workflow without unnecessary branch count details.

---

## TL;DR
- Branch from `main` using `feature/<issueId>-<slug>` (or `fix/<slug>` for small fixes).
- Use **Conventional Commits** for messages & PR titles.
- Ensure **Checkstyle**, **tests**, **coverage ≥ 80% (JaCoCo)**, and **PIT** all pass locally.
- Open a **PR to `main`** with a clear description and “Closes #<issueId>”.
- At least **one review** required; prefer **squash merge**.
- Tags `v*.*.*` trigger the **Release** workflow (JAR attached).

---

## 1) Prerequisites
- Java **17+**
- Maven **3.8+**
- GitHub account with access to this repo

---

## 2) Branching model
- Create short‑lived branches from `main`:
  - `feature/<issueId>-<short-slug>` — new features/refactors
  - `fix/<short-slug>` — small bugfixes
  - `hotfix/<short-slug>` — urgent fixes

**Examples**
- `feature/42-add-undo-action`
- `fix/ui-typo-you-lose`
- `hotfix/crash-on-empty-grid`

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
```bash
git config commit.template .gitmessage.txt
```
Suggested `.gitmessage.txt`:
```text
<type>(<scope>): <short summary>

# Why / What / How
# Breaking changes (if any)

Closes #<issueId>
```

### 3.2) Enforce semantic PR titles (optional)
Add `.github/workflows/semantic-pr.yml`:
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

---

## 4) Issues → Pull Requests
- Open an issue using the right template (🐛 bug, 🧩 feature, 📝 docs).
- Create a branch from `main`: `feature/<issueId>-<slug>`.
- Implement with tests and docs updates if needed.
- Run locally:
```bash
mvn -q verify                                    # build + tests + JaCoCo report (≥ 80% gate)
mvn -q org.pitest:pitest-maven:mutationCoverage  # optional local PIT
```
- Open a PR to `main`:
  - Title with Conventional Commit style (see §3).
  - Description with context and “Closes #<issueId>”.
  - Fill the PR checklist (tests, screenshots if UI).

**Review & merge**
- ≥ 1 reviewer approval.
- Prefer squash merge.
- Keep PRs small; large PRs slow down review.

---

## 5) Quality gates (must pass)
- **Checkstyle**: no violations in new/changed code.
- **Unit tests**: stable & deterministic.
- **Coverage gate**: **JaCoCo ≥ 80%**.
- **Mutation testing**: **PIT** workflow must pass.

**CI workflows**
- `CI`
- `Checkstyle (lint)`
- `Mutation Testing (PIT)`
- `Release`
- `Telemetry`

---

## 6) Code style & structure
- Target **Java 17**.
- Prefer logging over `System.out.println`.
- Keep methods short; avoid deep nesting.
- Separate UI (Swing) from logic.

---

## 7) Testing guidelines
- One test class per public class when meaningful.
- Cover edge cases.
- Avoid flakiness — inject seeds if needed.

---

## 8) Labels & triage
- `needs triage` (default on new issues)
- `bug`, `enhancement`, `documentation`, `good first issue`, `help wanted`
- Use milestones for releases.

---

## 9) Security & responsible disclosure
If you find a vulnerability, **do not open a public issue**. Email: **semy.drif@student.unamur.be**.

---

## 10) Releasing
- Ensure `main` is green on CI.
- Tag with SemVer: `vMAJOR.MINOR.PATCH`.
- `Release` workflow publishes a GitHub Release with the JAR attached.
- Summarize changes (notable PRs, breaking changes).

---

## 11) Code of Conduct
This project abides by the **Contributor Covenant**.

---

## 12) Branch protection policy (for `main`)
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

