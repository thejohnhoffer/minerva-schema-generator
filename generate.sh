# Build generator
cd schema-generator
mvn clean package
cd ..
# Generate Schema
mkdir schema
java -jar schema-generator/target/minerva-1.5.0-jar-with-dependencies.jar > schema/index.schema.json
# Generate Markdown
pnpm jsonschema2md -d schema -o exhibit -h false -s proptable -s definedinfact -s nullablefact -s typesection
cd exhibit
# Remove prefix
for f in index-properties*; do mv "$f" "${f/index-properties-/}"; done
# Keep selected non-array definitions
mv 'stories-items-properties-waypoints-items-properties-lensing.md' 'lensing-items.md'
# Otherwise, remove non-array definitions
find . -type f ! \( -name '*items.md' -o -name 'index.md' \) -exec rm -f {} +
mv 'stories-items-properties-waypoints-items.md' 'waypoints-items.md'
rm 'stories-items-properties-waypoints-items-properties-pan-items.md'
rm 'groups-items-properties-descriptions-items.md'
rm 'groups-items-properties-channels-items.md'
rm 'groups-items-properties-colors-items.md'
rm 'layout-properties-grid-items-items.md'
rm 'layout-properties-grid-items.md'
sed -i '' 's/(\[.*\](.*))$//g' *.md
sed -i '' 's/is required$/required/g' *.md
sed -i '' 's/is optional$/optional/g' *.md
sed -i '' 's/items Properties//g' *.md
for f in *items.md; do mv "$f" "${f/-items/}"; done
cd ..
# Generate HTML
cp template.html exhibit/template.html
pnpm markdown-folder-to-html exhibit
mv _exhibit exhibit/build
rm -rf schema
