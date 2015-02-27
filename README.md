# flycouchdb-webproject-example

[![Build Status](https://travis-ci.org/haduart/flycouchdb-webproject-example.svg)](https://travis-ci.org/haduart/flycouchdb-webproject-example) [![Dependency Status](https://www.versioneye.com/user/projects/54f0e4974f3108959a00052d/badge.svg?style=flat)](https://www.versioneye.com/user/projects/54f0e4974f3108959a00052d)
[1.1]: http://i.imgur.com/tXSoThF.png (twitter icon with padding)
[1]: https://twitter.com/flycouchdb
[2]: https://twitter.com/haduart
It's a real example of how to run [FlyCouchDB](https://github.com/haduart/flycouchdb)[![alt text][1.1]][1] migrations tool inside a WAR project.

## Usage

Build it:
```bash
lein do clean, ring uberwar
```

And run it:
```bash
 lein ring server
```
 or just deploy it in your favourite server :wink:.

From FlyCouchDB 0.2.1 it supports JBoss Virtual File System. Tested in Tomcat, Jetty and JBoss/Wildfly.

## Contributors

* **(Author)** [Eduard Cespedes Borras](https://github.com/haduart)[![alt text][1.1]][2]
* [Roberto Barchino Garrido](https://github.com/fisoide)
* [Igor Ruzanov](https://github.com/r00z)
* [Jeroen Hoekx](https://github.com/jhoekx)


## Sponsored

This project is sponsored by [D square N.V](http://dsquare.be)

## License

BSD.  See the LICENSE file at the root of this repository.