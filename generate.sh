cd schema-generator
mvn clean package
cd ..
java -jar schema-generator/target/minerva-1.5.0-jar-with-dependencies.jar > schema/index.schema.json
pnpm jsonschema2md -d schema -o exhibit
pnpm markdown-folder-to-html exhibit
mv _exhibit exhibit/build
