rem kompiliert die angular-app "tutorial" in tomcat's web-ordner

cd ../angular/tutorial
ng build --prod --delete-output-path false --output-hashing none

