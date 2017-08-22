#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_e450ed72b5c8_key -iv $encrypted_e450ed72b5c8_iv -in cd/signingkey.asc.enc -out cd/signingkey.asc -d
    gpg --fast-import cd/signingkey.asc
fi
