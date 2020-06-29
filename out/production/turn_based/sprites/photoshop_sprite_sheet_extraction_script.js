var originalUnit = preferences.rulerUnits;
preferences.rulerUnits = Units.INCHES;

var docRef = app.documents.add(2, 4);

var artLayerRef = docRef.artLayers.add();
artLayerRef.kind =  LayerKind.TEXT;

var textItemRef = artLayerRef.textItem;
textItemRef.contens = "Hello, World";

docRef = null;
artLayerRef = null;
textItemRef = null;

app.preferences.rulerUnits = originalUnit;

