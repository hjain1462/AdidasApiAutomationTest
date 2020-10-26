#!/usr/bin/env bash
export URI=https://petstore.swagger.io

if [ "$1" = "-s" ] && [ ! -z $2 ] ; then
  mvn -P integration-tests verify -Dskip.unit.tests=true -Dcucumber.options='--tags @api,@ui' -s $2
  sleep 5s
  mvn -P integration-failed-tests verify -Dskip.unit.tests=true -Dcucumber.options='--tags @api,@ui' -s $2
else
  mvn -P integration-tests verify -Dskip.unit.tests=true -Dcucumber.options='--tags @api,@ui'
  sleep 5s
  mvn -P integration-failed-tests verify -Dskip.unit.tests=true -Dcucumber.options='--tags @api,@ui'
fi