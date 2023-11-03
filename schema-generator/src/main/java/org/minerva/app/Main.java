package org.minerva.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saasquatch.jsonschemainferrer.AdditionalPropertiesPolicies;
import com.saasquatch.jsonschemainferrer.TitleDescriptionGenerators;
import com.saasquatch.jsonschemainferrer.EnumExtractors;
import com.saasquatch.jsonschemainferrer.FormatInferrers;
import com.saasquatch.jsonschemainferrer.JsonSchemaInferrer;
import com.saasquatch.jsonschemainferrer.RequiredPolicies;
import com.saasquatch.jsonschemainferrer.SpecVersion;
import java.util.Arrays;

public class Main {

  private static final ObjectMapper mapper = new ObjectMapper();
  private static final JsonSchemaInferrer inferrer = JsonSchemaInferrer.newBuilder()
      .setSpecVersion(SpecVersion.DRAFT_2019_09)
      // Requires commons-validator
      .setTitleDescriptionGenerator(TitleDescriptionGenerators.useFieldNamesAsTitles())
      .addFormatInferrers(FormatInferrers.email(), FormatInferrers.ip())
      .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.notAllowed())
      .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
      .addEnumExtractors(EnumExtractors.validEnum(java.time.Month.class),
          EnumExtractors.validEnum(java.time.DayOfWeek.class))
      .build();

  public static void main(String[] args) throws Exception {

    final JsonNode sample1 = mapper.readTree(
"{ \"Images\": [ { \"Name\": \"i0\", \"Description\": \"\", \"Path\": \"data/images/P37_S29-CRC01\", \"Width\": 78417, \"Height\": 57360, \"MaxLevel\": 7 } ], \"Header\": \"View an [H&E lens with multimodal lensing](#s=1w=0) a customized group of CyCIF channels. Click the settings icon to colorize or hide channels.\", \"Rotation\": 0, \"Layout\": { \"Grid\": [ [ \"i0\" ] ] }, \"Stories\": [ { \"Name\": \"\", \"Description\": \"\", \"Waypoints\": [ { \"Name\": \"Multimodal Lensing\", \"Description\": \"---\\n# H&E lens over group of CyCIF markers\", \"Arrows\": [], \"Overlays\": [], \"Group\": \"Overview\", \"Masks\": [], \"ActiveMasks\": [], \"Zoom\": 1, \"Pan\": [ 0.5, 0.5 ], \"Lensing\": { \"Group\": \"H&E\", \"Rad\": 150 } }, { \"Name\": \"Multimodal\", \"Description\": \"---\\n# group of CyCIF markers\", \"Arrows\": [], \"Overlays\": [], \"Group\": \"Overview\", \"Masks\": [], \"ActiveMasks\": [], \"Zoom\": 1, \"Pan\": [ 0.5, 0.5 ] } ] } ], \"Channels\": [ { \"Rendered\": true, \"Name\": \"Hematoxylin\", \"Path\": \"he\" }, { \"Rendered\": true, \"Name\": \"Eosin\", \"Path\": \"he\" }, { \"Name\": \"Hoechst\", \"Path\": \"Hoechst_0__Hoechst\" }, { \"Name\": \"Autofluorescence\", \"Path\": \"Autofluorescence_1__Autofluorescence\" }, { \"Name\": \"CD31\", \"Path\": \"CD31_2__CD31\" }, { \"Name\": \"alpha-SMA\", \"Path\": \"alpha-SMA_18__alpha-SMA\" }, { \"Name\": \"CD4\", \"Path\": \"CD4_6__CD4\" }, { \"Name\": \"FOXP3\", \"Path\": \"FOXP3_7__FOXP3\" }, { \"Name\": \"CD8a\", \"Path\": \"CD8a_8__CD8a\" }, { \"Name\": \"E-cadherin\", \"Path\": \"E-cadherin_14__E-cadherin\" }, { \"Name\": \"CD68\", \"Path\": \"CD68_4__CD68\" }, { \"Name\": \"PD-L1\", \"Path\": \"PD-L1_11__PD-L1\" }, { \"Name\": \"CD163\", \"Path\": \"CD163_13__CD163\" }, { \"Name\": \"Ki67\", \"Path\": \"Ki67_16__Ki67\" }, { \"Name\": \"CD45RO\", \"Path\": \"CD45RO_9__CD45RO\" }, { \"Name\": \"CD20\", \"Path\": \"CD20_10__CD20\" }, { \"Name\": \"CD3e\", \"Path\": \"CD3e_12__CD3e\" }, { \"Name\": \"PD-1\", \"Path\": \"PD-1_15__PD-1\" }, { \"Name\": \"CD45\", \"Path\": \"CD45_3__CD45\" }, { \"Name\": \"Pan-CK\", \"Path\": \"Pan-CK_17__Pan-CK\" } ], \"Masks\": [], \"Groups\": [ { \"Name\": \"H&E\", \"Colors\": [ \"ff00ff\", \"800080\" ], \"Channels\": [ \"Hematoxylin\", \"Eosin\" ], \"Descriptions\": [ \"Hematoxylin brightfield\", \"Eosin brightfield\" ] }, { \"Name\": \"Autofluorescence\", \"Colors\": [ \"ffffff\", \"7fd1ff\", \"ffff00\", \"ff0000\" ], \"Channels\": [ \"Hoechst\", \"Autofluorescence\", \"CD31\", \"alpha-SMA\" ], \"Descriptions\": [ \"_\", \"_\", \"_\", \"_\" ] }, { \"Name\": \"Immune 3\", \"Colors\": [ \"ffffff\", \"ff4298\", \"ffb500\", \"00ff00\", \"007fff\" ], \"Channels\": [ \"Hoechst\", \"CD4\", \"FOXP3\", \"CD8a\", \"E-cadherin\" ], \"Descriptions\": [ \"_\", \"_\", \"_\", \"_\", \"_\" ] }, { \"Name\": \"Immune 2\", \"Colors\": [ \"ffffff\", \"00ff00\", \"ff00ff\", \"ffff00\", \"007fff\" ], \"Channels\": [ \"Hoechst\", \"CD68\", \"PD-L1\", \"CD163\", \"Ki67\" ], \"Descriptions\": [ \"_\", \"_\", \"_\", \"_\", \"_\" ] }, { \"Name\": \"Immune 1\", \"Colors\": [ \"ffffff\", \"ff9b00\", \"ff00ff\", \"0000ff\", \"00ff00\" ], \"Channels\": [ \"Hoechst\", \"CD45RO\", \"CD20\", \"CD3e\", \"PD-1\" ], \"Descriptions\": [ \"_\", \"_\", \"_\", \"_\", \"_\" ] }, { \"Name\": \"Overview\", \"Colors\": [ \"e0e0e0\", \"ffff00\", \"ff8000\", \"00ff00\" ], \"Channels\": [ \"Hoechst\", \"CD31\", \"CD45\", \"Pan-CK\" ], \"Descriptions\": [ \"nuclear marker\", \"Endothelium (blood vessels)\", \"All immune cells\", \"Normal epithelium\", \"blood vessels & subepithelial myofibroblasts\" ] } ] }");
    final JsonNode resultForSample1 = inferrer.inferForSample(sample1);
    final JsonNode resultForSamples =
        inferrer.inferForSamples(Arrays.asList(sample1/*, sample2 */));
    for (JsonNode j : Arrays.asList(resultForSamples)) {
      System.out.println(mapper.writeValueAsString(j));
    }
  }

}
