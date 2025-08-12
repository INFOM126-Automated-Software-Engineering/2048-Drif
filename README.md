# 2048 (Java/Swing)

&#x20; &#x20;

A clean Java 17 implementation of the classic **2048** game (Swing). The repo ships with a complete CI: tests + coverage (JaCoCo, 80% gate), Checkstyle, PIT mutation testing, release packaging, and telemetry.

> If your repository name differs, update the badge URLs above.

---

## Features

- Solid **grid mechanics** (move/merge, chain merges)
- **Keyboard controls** (arrow keys)
- **Win/lose** detection with clear messages
- GitHub Release with packaged JAR (on tags)

---

## Requirements

- **Java 17+**
- **Maven 3.8+**

---

## Quick start

### Build & test (with coverage)

```bash
mvn -q verify
```

### Run

From your IDE, run the `be.unamur.game2048.Game` class. If the JAR is configured as executable, you can also:

```bash
java -jar target/2048-1.0-SNAPSHOT.jar
```

> If the JAR is not executable, set the `Main-Class` via `maven-jar-plugin` with `be.unamur.game2048.Game`.

---

## Project structure

```
.
├─ src/
│  ├─ main/java/be/unamur/game2048/...
│  └─ test/java/be/unamur/game2048/...
├─ docs/
│  └─ CONTRIBUTOR.md
└─ .github/workflows/
   ├─ ci.yml
   ├─ checkstyle.yml
   ├─ pit.yml
   ├─ release.yml (or release-tag-and-upload.yml)
   └─ telemetry.yml
```

---

## Quality gates & CI

- **Checkstyle (lint)** — code style enforcement
- **Unit tests** — run on every push/PR
- **Coverage gate** — build fails if **JaCoCo < 80%**
- **Mutation testing (PIT)** — validates test robustness
- **Release** — on tag `v*.*.*`, builds and attaches the JAR
- **Telemetry** — summarizes workflow duration & status

See workflow runs under **Actions**.

---

## Contributing

We welcome contributions! Please read:

- [`docs/CONTRIBUTOR.md`](docs/CONTRIBUTOR.md)
- [`CODE_OF_CONDUCT.md`](CODE_OF_CONDUCT.md)

Open issues with the right template and label. For questions/ideas, use **Discussions**:

- [https://github.com/sdrif/2048-Drif/discussions](https://github.com/sdrif/2048-Drif/discussions)

---

## License

See [`LICENSE`](LICENSE).

---

## Acknowledgements

Inspired by the many open-source 2048 implementations.

