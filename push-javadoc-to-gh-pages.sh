#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "mnoman09/java-sdk" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ]; then
  echo -e "Publishing javadoc...\n"

  cp -R core-api/build/docs/javadoc $HOME/javadoc-latest

  cd $HOME
  git config --global user.email "muhammadnoman@folio3.com"
  git config --global user.name "mnoman09"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/mnoman09/java-sdk gh-pages > /dev/null

  cd gh-pages
  git rm -rf ./javadoc
  cp -Rf $HOME/javadoc-latest ./javadoc
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"
fi
