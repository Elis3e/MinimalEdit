pages:
 script:
 - mkdir .public
 - cp -r * .public
 - mv .public public
 artifacts:
    paths:
    - public
