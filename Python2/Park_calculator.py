import json
import os
import Neighbourhood_fetcher
import Park_finder

def save_top3(top3):
    outpath = os.path.join(os.path.dirname(__file__), "top3_neighbourhoods.json")
    with open(outpath, "w") as f:
        json.dump(top3, f, indent=2)
    print("Saved top 3 to:", outpath)


def save_results(all_counts):
    # all_counts = list of dicts: {neighbourhood, lat, lon, park_count}

    # Sort whole list
    sorted_all = sorted(all_counts, key=lambda r: r["parks"], reverse=True)

    # Extract top 3
    top3 = sorted_all[:3]

    # Final JSON structure
    final_data = {
        "neighbourhoods": sorted_all,
        "top3": top3
    }

    # Output file
    base_dir = os.path.dirname(os.path.abspath(__file__))
    out_path = os.path.join(base_dir, "JSON", "park_results.json")

    with open(out_path, "w") as f:
        json.dump(final_data, f, indent=4)

    print(f"[OK] Saved neighbourhood + park data to {out_path}")


def main():
    # 1) Fetch neighbourhoods
    Neighbourhood_fetcher.main()
    nh_list = Neighbourhood_fetcher.load_neighbourhoods()

    # 2) Fetch all parks (cached inside Park_finder)
    Park_finder.fetch_all_parks_LA()

    results = []  # <-- THIS replaces neighbourhood_park_counts

    for nh in nh_list:
        name = nh.get("tags", {}).get("name", "Unknown")
        lat = nh.get("lat")
        lon = nh.get("lon")

        if lat is None or lon is None:
            continue

        count = Park_finder.count_parks_within_radius(lat, lon, 1000)

        results.append({
            "name": name,
            "lat": lat,
            "lon": lon,
            "parks": count
        })

        print(f"{name}: {count} parks")

    # Sort + top 3 (for quick saving separately)
    top3 = sorted(results, key=lambda r: r["parks"], reverse=True)[:3]
    save_top3(top3)

    # Save full results (all neighbourhoods)
    save_results(results)


if __name__ == "__main__":
    main()
