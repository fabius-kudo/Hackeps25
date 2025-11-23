import pandas as pd
import numpy as np
from scipy.spatial import cKDTree
import json
import os

# ------------------------
# Step 1: File paths
# ------------------------
neighborhoods_file = os.path.join("JSON", "la_neighborhoods_raw.json")
crime_file = os.path.join("JSON", "crimes.json")
crime_counts_file = os.path.join("JSON", "crime_counts.json")  # optional

# ------------------------
# Step 2: Load neighborhoods JSON
# ------------------------
with open(neighborhoods_file, 'r', encoding='utf-8') as f:
    neighborhoods_json = json.load(f)

# Extract 'name', 'lat', 'lon' from Overpass-style nodes
neighborhoods = pd.DataFrame([{
    'name': node['tags'].get('name'),
    'lat': node['lat'],
    'lon': node['lon']
} for node in neighborhoods_json])

# ------------------------
# Step 3: Load crime points JSON
# ------------------------
with open(crime_file, 'r', encoding='utf-8') as f:
    crime_json = json.load(f)

# Extract lat, lon, and area_name (adjust key names if needed)
crime_points = pd.DataFrame([{
    'lat': c['lat'],
    'lon': c['lon'],
    'area_name': c['area_name']
} for c in crime_json])

# ------------------------
# Step 4: Load pre-calculated crime counts
# ------------------------
with open(crime_counts_file, 'r', encoding='utf-8') as f:
    crime_counts_json = json.load(f)

crime_counts = pd.DataFrame(crime_counts_json)  # expects [{'area_name':..., 'crime_count':...}, ...]

# ------------------------
# Step 5: Build KDTree for nearest-neighbor search
# ------------------------
tree = cKDTree(crime_points[['lat', 'lon']].values)

# Query nearest crime point for each neighborhood
distances, indices = tree.query(neighborhoods[['lat', 'lon']].values, k=1)

# Assign area_name from nearest crime point
neighborhoods['area_name'] = crime_points.loc[indices, 'area_name'].values

# ------------------------
# Step 6: Merge with pre-calculated crime counts
# ------------------------
neighborhoods = neighborhoods.merge(crime_counts, on='area_name', how='left')

# ------------------------
# Step 7: Save or display the result
# ------------------------
output_file = os.path.join("JSON", "neighborhoods_with_crime_counts.csv")
neighborhoods.to_csv(output_file, index=False)

print(neighborhoods[['name', 'area_name', 'crime_count']])
