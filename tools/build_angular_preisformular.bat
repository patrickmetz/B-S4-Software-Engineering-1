rem kompiliert die angular-app "parkhaus" in tomcat's web-ordner

cd ../angular/parkhaus/files
ng build --prod --delete-output-path false --output-hashing none --base-href /

