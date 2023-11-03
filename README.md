# Minerva Schema generator

This package uses a JSON schema generator in Java [Using JSON schema 2019-09](https://json-schema.org/draft/2019-09/release-notes) then converts the schema to HTML using two Javascript dpendencies.

## Installation

Install [(Java JDK 21)](https://www.oracle.com/java/technologies/downloads/#java21), then install maven.

```bash
curl --output maven.zip https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.zip
unzip maven.zip
echo "export PATH=$(pwd)/apache-maven-3.9.5/bin:\$PATH" >> ~/.bashrc
```

Install Javascript dependencies

```bash
pnpm install
```

## Schema generation

Package and run jar:

```bash
cd schema-generator
mvn clean package
cd ..
java -jar schema-generator/target/minerva-1.5.0-jar-with-dependencies.jar > schema/index.schema.json
```

Generate Markdown from schema:

```bash
pnpm jsonschema2md -d schema -o exhibit
```

Generate HTML:

```bash
pnpm markdown-folder-to-html exhibit
mv _exhibit exhibit/build
```

To re-run, `rm -rf exhibit` and `bash generate.sh`

See the generated [json-schema documentation](./exhibit/build/index.html)
