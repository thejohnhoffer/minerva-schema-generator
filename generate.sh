# Build generator
cd schema-generator
mvn clean package
cd ..
# Generate Schema
mkdir schema
java -jar schema-generator/target/minerva-1.5.0-jar-with-dependencies.jar > schema/index.schema.json
# Generate Markdown
pnpm jsonschema2md -d schema -o exhibit -h false -s typefact -s proptable
cd exhibit
find . -type f ! \( -name '*items.md' -o -name 'index.md' \) -exec rm -f {} +
for f in index-properties*; do mv "$f" "${f/index-properties-/}"; done
sed -i '' 's/.*defined in.*//g' *.md
rm 'stories-items-properties-waypoints-items-properties-pan-items.md'
rm 'groups-items-properties-descriptions-items.md'
rm 'groups-items-properties-channels-items.md'
rm 'groups-items-properties-colors-items.md'
rm 'layout-properties-grid-items-items.md'
rm 'layout-properties-grid-items.md'
cd ..
# Generate HTML
pnpm markdown-folder-to-html exhibit
mv _exhibit exhibit/build
rm -rf schema
