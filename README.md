sbt-git-stamp
=============

An [sbt](https://www.scala-sbt.org/) plugin that adds some basic [Git](https://git-scm.com/) data to the artefact's `MANIFEST.MF` file. This repository is a fork of the [hmrc/sbt-git-stamp](https://github.com/hmrc/sbt-git-stamp). All credit goes to the original plugin [authors](https://github.com/hmrc/sbt-git-stamp/graphs/contributors).

The original plugin was released on JFrog's Bintray repository that has been closed on May 1, 2021. The plugin is no longer actively maintained, so its binaries haven't been moved to any alternative repository. This fork solves this problem as its binaries are published to the [Maven Central](https://search.maven.org/) repository.

Version 1.0.0 of this fork corresponds to the last version of the original [hmrc/sbt-git-stamp](https://github.com/hmrc/sbt-git-stamp) plugin:

| `nescale/sbt-git-stamp` | [`hmrc/sbt-git-stamp`](https://github.com/hmrc/sbt-git-stamp) |
| ----------------------- | ------------------------------------------------------------- |
| `1.0.0`                 | `6.2.0`                                                       |

## What it does

The plugin adds some basic information about the state of the repository that the artifact was built from (at the time it was built) to the `MANIFEST.MF` file. This information includes:

* head revision
* branch name
* whether or not there were uncommitted changes
* build date

This information is included in the `MANIFEST.MF` file in the JAR produced by the sbt `package` (or `assembly`) tasks. This can help you track down where a build came from.

## How to use it

Add the following to your `project/plugins.sbt`:

```sbt
addSbtPlugin("com.nescale" % "sbt-git-stamp" % "1.0.0")
```

Add the following near the top of your `build.sbt` file:

```sbt
import com.nescale.gitstamp.GitStampPlugin._
```

Then, add this to the `build.sbt` file as well:

```sbt
Seq(gitStampSettings: _*)
```

Then, just build your application as normal. This plugin won't add any tasks, or otherwise change the way you interact with sbt.

Your artifacts will just come out with a `MANIFEST.MF` file that looks something like this:

```
Manifest-Version: 1.0
Specification-Title: My-Company
Specification-Version: 0.1
Specification-Vendor: My-Company
Implementation-Title: My-Project
Implementation-Version: 0.1
Implementation-Vendor: My-Company
Implementation-Vendor-Id: My-Company
Git-Branch: main
Git-Head-Rev: 13081ca1a17c2fb99ddb67aba24a4180ce4eaf7d
Git-Repo-Is-Clean: false
Git-Commit-Date: 1652211774000
Git-Commit-Author: someuser
Build-Date: 2022-05-10T22:13:16.131493
Git-Describe: release/0.1.0-13081ca
```

The entries starting with `Git-` as well as `Build-Date` were added by this plugin.

## License

This code is open source software licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html).
